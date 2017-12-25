package com.searcin.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
	public Areas save(Areas areas) {
		return areasRepository.save(areas);
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
		if (vendorsRepository.countByAddressAreaId(id) > 0) {
			throw new ObjectRemovalException("The Area is associated with some vendor. Can't Remove temporarily!");
		}
		else {
			Areas area = findById(id);
			area.setIsActive(false);
			save(area);
		}
	}

	@Override
	@Transactional
	public void restore(Integer id) {
		Areas area = findById(id);
		area.setIsActive(true);
		save(area);
	}

	@Override
	public void delete(Integer id) {
		Areas area = findById(id);
		if (area.getIsActive()) {
			throw new ObjectRemovalException("The Area is active. Can't Remove permanently!");
		}
		areasRepository.delete(area);
	}

	@Override
	public void deleteAll() {
		areasRepository.deleteAll();
	}
}
