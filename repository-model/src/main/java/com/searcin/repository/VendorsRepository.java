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
	
	@Query("select v from Vendors v where v.isActive = true")
	List<Vendors> findAll();
	
	@Query("select new Vendors(v.id,v.name) from Vendors v where v.isActive = true")
	List<Vendors> findNames();
	
	@Query("select new Vendors(v.id,v.name) from Vendors v where v.isActive = true")
	Page<Vendors> findNames(Pageable pageable);
	
	Vendors findById(Integer id);
	
	@Query("select new Vendors(v.id,v.address) from Vendors v where v.isActive = true and v.id = ?1")
	Vendors findAddressByVendor(Integer id);

	@Query("select new Vendors(v.id,v.contact) from Vendors v where v.isActive = true and v.id = ?1")
	Vendors findContactByVendor(Integer id);
	
	Integer countByAddressAreaId(Integer id);
	
	Integer countBySubCategoryId(Integer id);
	
	Integer countByServicesId(Integer id);
	
	Vendors save(Vendors vendors);
	
	List<Vendors> findByCategory_id(Integer id);

	List<Vendors> findBySubCategory_id(Integer id);
	
	void delete(Vendors vendor);
	
	void deleteAll();

	void deleteById(Integer id);

	Vendors saveAndFlush(Vendors vendors);

}
