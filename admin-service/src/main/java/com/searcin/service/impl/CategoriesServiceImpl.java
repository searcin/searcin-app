package com.searcin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.searcin.entity.Categories;
import com.searcin.repository.CategoriesRepository;
import com.searcin.service.CategoriesService;

@Service
public class CategoriesServiceImpl implements CategoriesService {

	@Autowired
	private CategoriesRepository categoriesRepository;

	public List<Categories> findAll() {
		return categoriesRepository.findAll();
	}

	@Override
	public Categories save(Categories category) {
		return categoriesRepository.save(category);
	}

	@Override
	public void delete(Integer id) {
		categoriesRepository.delete(categoriesRepository.findById(id));
	}

	@Override
	public void deleteAll() {
		categoriesRepository.deleteAll();
	}

	@Override
	public Categories findById(Integer id) {
		return categoriesRepository.findById(id);
	}

	@Override
	public List<Categories> findNames() {
		return categoriesRepository.findNames();
	}

	@Override
	public Page<Categories> findPage(Pageable pageable) {
		return categoriesRepository.findAll(pageable);
	}

}
