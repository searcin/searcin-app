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

	void deleteAll();

	Areas findById(Integer id);
	
	void delete(Integer id);

	void restore(Integer id);
	
	void trash(Integer id);

}
