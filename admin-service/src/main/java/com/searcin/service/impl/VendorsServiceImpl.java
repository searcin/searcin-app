package com.searcin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.searcin.entity.Addresses;
import com.searcin.entity.Contacts;
import com.searcin.entity.Services;
import com.searcin.entity.Vendors;
import com.searcin.exception.ObjectNotFoundException;
import com.searcin.repository.ServicesRepository;
import com.searcin.repository.VendorsRepository;
import com.searcin.service.VendorAssetService;
import com.searcin.service.VendorsService;

@Service
public class VendorsServiceImpl implements VendorsService {

	@Autowired
	private VendorsRepository vendorsRepository;

	@Autowired
	private ServicesRepository servicesRepository;
	
	@Value("${assets.vendor}")
	private String vendor;	

	@Autowired
	private VendorAssetService vendorAssetService;
	
	@Override
	public List<Vendors> findAll() {
		return vendorsRepository.findAll();
	}
	
	@Override
	public Vendors findById(Integer id) {
		return Optional.ofNullable(vendorsRepository.findById(id))
				.orElseThrow(() -> new ObjectNotFoundException("Vendor not found!"));
	}
	
	@Override
	public Addresses findAddress(Integer id) {
		return findById(id).getAddress();
	}
	
	@Override
	public Contacts findContact(Integer id) {
		return findById(id).getContact();
	}

	@Override
	public void saveAddress(Addresses address, Integer id) {
		Vendors vendor = findById(id);
		vendor.setAddress(address);
		save(vendor);
	}
	
	@Override
	public void saveContact(Contacts contact, Integer id) {
		Vendors vendor = findById(id);
		vendor.setContact(contact);
		save(vendor);
	}	

	@Override
	public List<Vendors> findNames() {
		return vendorsRepository.findNames();
	}

	@Override
	public Vendors save(Vendors vendors) {
		return vendorsRepository.save(vendors);
	}

	@Override
	public void delete(Integer id) {
		//delete assets
		vendorAssetService.deleteAssetsByVendor(id);
		//delete row
		vendorsRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		vendorsRepository.deleteAll();
	}

	@Override
	public List<Vendors> findByCategoryId(Integer category_id) {
		return vendorsRepository.findByCategory_id(category_id);
	}

	@Override
	public List<Vendors> findBySubCategoryId(Integer sub_category_id) {
		return vendorsRepository.findBySubCategory_id(sub_category_id);
	}

	@Override
	public void saveServices(Integer vendorId, List<Integer> services) {
		Vendors vendor = findById(vendorId);
		List<Services> listOfServices = new ArrayList<>();
		for (Integer id : services) {
			listOfServices.add(servicesRepository.findById(id));
		}
		vendor.setServices(listOfServices);
		save(vendor);
	}

	@Override
	public Vendors detail(Integer id) {
		return vendorsRepository.findById(id);
	}

	@Override
	public Page<Vendors> findNames(Pageable pageable) {
		return vendorsRepository.findNames(pageable);
	}

	@Override
	public void restore(Integer id) {
		Vendors vendor = findById(id);
		vendor.setIsActive(true);
		save(vendor);
	}

	@Override
	public void trash(Integer id) {
		Vendors vendor = findById(id);
		vendor.setIsActive(false);
		save(vendor);
	}

	
}
