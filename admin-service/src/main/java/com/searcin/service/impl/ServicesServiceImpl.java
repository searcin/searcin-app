package com.searcin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.searcin.entity.Services;
import com.searcin.repository.ServicesRepository;
import com.searcin.service.ServicesService;

@Service
public class ServicesServiceImpl implements ServicesService {
	
	@Autowired
	private ServicesRepository servicesRepository;
	
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
		return servicesRepository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		servicesRepository.delete(findById(id));
	}

	@Override
	public List<Services> findNames() {
		return servicesRepository.findNames();
	}

	@Override
	public Page<Services> findPage(Pageable pageable) {
		return servicesRepository.findAll(pageable);
	}

}
