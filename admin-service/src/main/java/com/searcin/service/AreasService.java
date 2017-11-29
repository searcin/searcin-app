package com.searcin.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.searcin.entity.Areas;

public interface AreasService {
	
	List<Areas> findNames();

	List<Areas> findAll();
	
	Page<Areas> findPage(Pageable pageable);
	
	Areas save(Areas areas);

	void delete(Areas area);

	void deleteAll();

	Areas findById(Integer id);

}
