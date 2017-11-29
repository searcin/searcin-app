package com.searcin.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.searcin.entity.Vendors;

public interface VendorsService {

	List<Vendors> findNames();
	
	List<Vendors> findAll();

	Vendors save(Vendors vendors);

	void saveServices(Integer id, List<Integer> services);

	void delete(Integer id);

	void deleteAll();

	Vendors findById(Integer id);

	List<Vendors> findByCategoryId(Integer category_id);

	List<Vendors> findBySubCategoryId(Integer sub_category_id);

	Vendors detail(Integer id);

	Page<Vendors> findNames(Pageable pageable);

}
