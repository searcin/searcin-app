package com.searcin.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.searcin.entity.SubCategories;

@Transactional
public interface SubCategoriesRepository extends Repository<SubCategories, Long> {

	SubCategories save(SubCategories subCategories);

	SubCategories findById(Integer id);
	
	void delete(SubCategories subCategory);
	
	void deleteAll();
	
	List<SubCategories> findAll();
	
	@Query("select new SubCategories(s.id,s.name) from SubCategories s")
	List<SubCategories> findNames();

	List<SubCategories> findByCategory_id(Integer id);

	Page<SubCategories> findByCategory_id(Integer id, Pageable pageable);
}
