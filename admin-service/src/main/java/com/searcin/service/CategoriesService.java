package com.searcin.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.searcin.entity.Categories;

public interface CategoriesService {
	
	List<Categories> findNames();
	
	List<Categories> findAll();

	Categories save(Categories category);

	void delete(Integer id);

	void deleteAll();

	Categories findById(Integer id);

	Page<Categories> findPage(Pageable pageable);

	void trash(Integer id);

	void restore(Integer id);
}
