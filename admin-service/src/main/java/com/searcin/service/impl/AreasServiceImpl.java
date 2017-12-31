package com.searcin.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.searcin.entity.Areas;
import com.searcin.exception.ObjectNotFoundException;
import com.searcin.exception.ObjectRemovalException;
import com.searcin.repository.AreasRepository;
import com.searcin.repository.VendorsRepository;
import com.searcin.service.AreasService;

@Service
public class AreasServiceImpl implements AreasService {
	
	private final Logger log = LoggerFactory.getLogger(AreasServiceImpl.class);

	@Autowired
	private AreasRepository areasRepository;

	@Autowired
	private VendorsRepository vendorsRepository;

	@Override
	public List<Areas> findAll() {
		return areasRepository.findAll();
	}

	@Override
	public Page<Areas> findPage(Pageable page) {
		return areasRepository.findAll(page);
	}

	@Override
	public Areas save(Areas area) {
		area =  areasRepository.save(area);
		log.info("Area saved/updated to database {}", area);
		return area;
	}

	@Override
	public Areas findById(Integer id) {
		return Optional.ofNullable(areasRepository.findById(id))
				.orElseThrow(() -> new ObjectNotFoundException("Area Not Found!"));
	}

	@Override
	public List<Areas> findNames() {
		return areasRepository.findNames();
	}

	@Override
	public void trash(Integer id) {
		Integer numOfVendors = vendorsRepository.countByAddressAreaId(id);
		if (numOfVendors > 0) {
			log.error("Number of vendors associated with that area id {}. So can't trash!", numOfVendors);
			throw new ObjectRemovalException("The Area is associated with some vendor. Can't Remove temporarily!");
		}
		else {
			log.info("Area trashing from database {}", id);
			areasRepository.update(false, id);
		}
	}

	@Override
	public void restore(Integer id) {
		areasRepository.update(true, id);
		log.info("Area restored from database {}", id);
	}

	@Override
	public void delete(Integer id) {
		Areas area = findById(id);
		if (area.getIsActive()) {
			log.error("Area is active! {}", area);
			throw new ObjectRemovalException("The Area is active. Can't Remove permanently!");
		}
		log.info("Removing area permanently from database {}", area);
		areasRepository.delete(area);
	}

	@Override
	public void deleteAll() {
		areasRepository.deleteAll();
	}
}
