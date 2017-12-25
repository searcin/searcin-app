package com.searcin.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.searcin.entity.Categories;


@Transactional
public interface CategoriesRepository extends Repository<Categories, Long> {
	
	Categories findById(Integer id);
	
	@Query("select new Categories(c.id,c.name) from Categories c where c.isActive = true")
	List<Categories> findNames();
	
	@Query("select c from Categories c where c.isActive = true")
	List<Categories> findAll();

	Categories save(Categories category);

	void delete(Categories category);

	void deleteAll();

	Page<Categories> findAll(Pageable pageable);
}
