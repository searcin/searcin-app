package com.searcin.repository;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.Repository;

import com.searcin.entity.Addresses;

@Transactional
public interface AddressesRepository extends Repository<Addresses,Long>{

	List<Addresses> findAll();

	Addresses save(Addresses addresses);

	//Addresses findByVendor_id(Integer id);

}
