package com.searcin.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.searcin.entity.SubCategories;

public interface SubCategoriesService {

	SubCategories save(SubCategories subCategories);

	SubCategories findById(Integer id);

	void delete(Integer id);

	void deleteAll();

	List<SubCategories> findAll();
	
	List<SubCategories> findNames();

	List<SubCategories> findByCategoryId(Integer category_id);

	Page<SubCategories> findByCategoryId(Integer id, Pageable pageable);

}
