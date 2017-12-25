package com.searcin.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.searcin.entity.Services;
import com.searcin.exception.ObjectNotFoundException;
import com.searcin.exception.ObjectRemovalException;
import com.searcin.repository.ServicesRepository;
import com.searcin.repository.VendorsRepository;
import com.searcin.service.ServicesService;

@Service
public class ServicesServiceImpl implements ServicesService {

	private Logger log = LoggerFactory.getLogger(ServicesServiceImpl.class);

	@Autowired
	private ServicesRepository servicesRepository;

	@Autowired
	private VendorsRepository vendorsRepository;

	@Override
	public List<Services> findAll() {
		return servicesRepository.findAll();
	}

	@Override
	public Services save(Services service) {
		return servicesRepository.save(service);
	}

	@Override
	public Services findById(Integer id) {
		return Optional.ofNullable(servicesRepository.findById(id))
				.orElseThrow(() -> new ObjectNotFoundException("Service not found"));
	}

	@Override
	public void deleteById(Integer id) {
		Services service = findById(id);
		if (service.getIsActive())
			throw new ObjectRemovalException("Service is active! can't remove permanently!");
		else
			servicesRepository.delete(service);
	}

	@Override
	public List<Services> findNames() {
		return servicesRepository.findNames();
	}

	@Override
	public Page<Services> findPage(Pageable pageable) {
		return servicesRepository.findAll(pageable);
	}

	@Override
	public void trash(Integer id) {
		Services service = findById(id);
		if (vendorsRepository.countByServicesId(id) > 0) {
			throw new ObjectRemovalException("Service is associated with some vendor. Can't remove temporarily!");
		} else {
			service.setIsActive(false);
			log.info("service trashed {}", service);
			save(service);
		}
	}

	@Override
	public void restore(Integer id) {
		Services service = findById(id);
		service.setIsActive(true);
		log.info("service restored {}", service);
		save(service);
	}

}
