package com.searcin.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.searcin.entity.Categories;
import com.searcin.exception.ObjectNotFoundException;
import com.searcin.exception.ObjectRemovalException;
import com.searcin.repository.CategoriesRepository;
import com.searcin.repository.SubCategoriesRepository;
import com.searcin.service.CategoriesService;

@Service
public class CategoriesServiceImpl implements CategoriesService {

	@Autowired
	private CategoriesRepository categoriesRepository;

	@Autowired
	private SubCategoriesRepository subCategoriesRepository;

	public List<Categories> findAll() {
		return categoriesRepository.findAll();
	}

	@Override
	public Categories save(Categories category) {
		return categoriesRepository.save(category);
	}

	@Override
	public Categories findById(Integer id) {
		return Optional.ofNullable(categoriesRepository.findById(id))
				.orElseThrow(() -> new ObjectNotFoundException("Category Not Found!"));
	}

	@Override
	public List<Categories> findNames() {
		return categoriesRepository.findNames();
	}

	@Override
	public Page<Categories> findPage(Pageable pageable) {
		return categoriesRepository.findAll(pageable);
	}

	@Override
	public void trash(Integer id) {
		if (subCategoriesRepository.countByCategory(id) > 0) {
			throw new ObjectRemovalException("Subcategoires present. Can't trash!");
		} else {
			Categories category = findById(id);
			category.setIsActive(false);
			save(category);
		}
	}

	@Override
	public void restore(Integer id) {
		Categories category = findById(id);
		category.setIsActive(true);
		save(category);
	}
	
	@Override
	public void delete(Integer id) {
		Categories category = findById(id);
		if (category.getIsActive())
			throw new ObjectRemovalException("The category is active! can't remove permanently.");
		else
			categoriesRepository.delete(category);
	}

	@Override
	public void deleteAll() {
		categoriesRepository.deleteAll();
	}
}
