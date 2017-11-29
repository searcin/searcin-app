package com.searcin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.searcin.entity.Areas;
import com.searcin.repository.AreasRepository;
import com.searcin.service.AreasService;

@Service
public class AreasServiceImpl implements AreasService {
	
	@Autowired
	private AreasRepository areasRepository;

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
	public void delete(Areas area) {
		areasRepository.delete(area);
	}

	@Override
	public void deleteAll() {
		areasRepository.deleteAll();
	}

	@Override
	public Areas findById(Integer id) {
		return areasRepository.findById(id);
	}

	@Override
	public List<Areas> findNames() {
		return areasRepository.findNames();
	}

}
