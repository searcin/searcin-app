package com.searcin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.searcin.entity.Addresses;
import com.searcin.repository.AddressesRepository;
import com.searcin.service.AddressesService;

@Service
public class AddressesServiceImpl implements AddressesService {

	@Autowired
	private AddressesRepository addressesRepository;
	
	@Override
	public List<Addresses> findAll() {
		return addressesRepository.findAll();
	}

	@Override
	public Addresses save(Addresses addresses) {
		return addressesRepository.save(addresses);
	}

	@Override
	public Addresses findByVendorId(Integer id) {
		return addressesRepository.findByVendor_id(id);
	}

}
