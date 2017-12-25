package com.searcin.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.searcin.entity.SubCategories;
import com.searcin.exception.ObjectNotFoundException;
import com.searcin.exception.ObjectRemovalException;
import com.searcin.repository.CategoriesRepository;
import com.searcin.repository.SubCategoriesRepository;
import com.searcin.repository.VendorsRepository;
import com.searcin.service.SubCategoriesService;

@Service
public class SubCategoriesServiceImpl implements SubCategoriesService {

	private final Logger log = LoggerFactory.getLogger(SubCategoriesServiceImpl.class);
	
	@Autowired
	private CategoriesRepository categoriesRepository;

	@Autowired
	private SubCategoriesRepository subCategoriesRepository;
	
	@Autowired
	private VendorsRepository vendorsRepository;

	@Override
	public SubCategories save(SubCategories subCategories, Integer id) {		
		subCategories.setCategory(Optional.ofNullable(categoriesRepository.findById(id))
									.orElseThrow(() -> new ObjectNotFoundException("Category not found!")));
		return subCategoriesRepository.save(subCategories);
	}

	@Override
	public SubCategories findById(Integer id) {
		return Optional.ofNullable(subCategoriesRepository.findById(id))
					.orElseThrow(() -> new ObjectNotFoundException("Sub-category not found!"));
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

	@Override
	public void trash(Integer id) {
		SubCategories subCategory = findById(id);
		if(vendorsRepository.countBySubCategoryId(id) > 0) {
			throw new ObjectRemovalException("The sub category is associated with some vendor. Can't delete temporarily!");
		}
		else {
			subCategory.setIsActive(false);
			log.info("Sub category trashed {}", subCategory);
			subCategoriesRepository.save(subCategory);
		}
	}

	@Override
	public void restore(Integer id) {
		SubCategories subCategory = findById(id);		
		subCategory.setIsActive(true);
		log.info("Sub category restored {}", subCategory);
		subCategoriesRepository.save(subCategory);
	}

}
