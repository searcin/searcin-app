package com.searcin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.searcin.entity.SubCategories;
import com.searcin.repository.SubCategoriesRepository;
import com.searcin.service.SubCategoriesService;

@Service
public class SubCategoriesServiceImpl implements SubCategoriesService {

	// private final Logger log =
	// LoggerFactory.getLogger(SubCategoriesServiceImpl.class);

	@Autowired
	private SubCategoriesRepository subCategoriesRepository;

	@Override
	public SubCategories save(SubCategories subCategories) {
		return subCategoriesRepository.save(subCategories);
	}

	@Override
	public SubCategories findById(Integer id) {
		return subCategoriesRepository.findById(id);
	}

	@Override
	public void delete(Integer id) {
		subCategoriesRepository.delete(subCategoriesRepository.findById(id));
	}

	@Override
	public void deleteAll() {
		subCategoriesRepository.deleteAll();
	}

	@Override
	public List<SubCategories> findAll() {
		return subCategoriesRepository.findAll();
	}

	@Override
	public List<SubCategories> findByCategoryId(Integer category_id) {
		return subCategoriesRepository.findByCategory_id(category_id);
	}

	@Override
	public List<SubCategories> findNames() {
		return subCategoriesRepository.findNames();
	}

	@Override
	public Page<SubCategories> findByCategoryId(Integer id, Pageable pageable) {
		return subCategoriesRepository.findByCategory_id(id, pageable);
	}

}
