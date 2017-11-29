package com.searcin.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.searcin.entity.Vendors;

@Transactional
public interface VendorsRepository extends Repository<Vendors, Long> {
	
	List<Vendors> findAll();
	
	@Query("select new Vendors(v.id,v.name) from Vendors v")
	List<Vendors> findNames();
	
	@Query("select new Vendors(v.id,v.name) from Vendors v")
	Page<Vendors> findNames(Pageable pageable);
	
	Vendors save(Vendors vendors);

	Vendors findById(Integer id);
	
	List<Vendors> findByCategory_id(Integer id);

	List<Vendors> findBySubCategory_id(Integer id);
	
	void delete(Vendors vendor);
	
	void deleteAll();

	void deleteById(Integer id);
}
