package com.searcin.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.searcin.entity.Services;

public interface ServicesService {

	List<Services> findAll();
	
	List<Services> findNames();

	Services save(Services service);

	Services findById(Integer id);

	void deleteById(Integer id);

	Page<Services> findPage(Pageable pageable);

	void trash(Integer id);

	void restore(Integer id);
	
}
