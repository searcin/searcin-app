package com.searcin.service;

import java.util.List;

import com.searcin.entity.Addresses;

public interface AddressesService {

	List<Addresses> findAll();

	Addresses save(Addresses convertToEntity);

	Addresses findByVendorId(Integer id);

}
