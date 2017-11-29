package com.searcin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.searcin.entity.Services;
import com.searcin.entity.Vendors;
import com.searcin.repository.CategoriesRepository;
import com.searcin.repository.ClassRangeRepository;
import com.searcin.repository.ServicesRepository;
import com.searcin.repository.SubCategoriesRepository;
import com.searcin.repository.VendorsRepository;
import com.searcin.service.VendorsService;

@Service
public class VendorsServiceImpl implements VendorsService {
	
	@Autowired
	private VendorsRepository vendorsRepository;
	
	@Autowired
	private ServicesRepository servicesRepository;
	
	@Autowired
	private ClassRangeRepository classRangeRepository;
	
	@Autowired
	private CategoriesRepository categoriesRepository;
	
	@Autowired
	private SubCategoriesRepository subCategoriesRepository;
	
	@Override
	public List<Vendors> findAll() {
		return vendorsRepository.findAll();
	}

	@Override
	public List<Vendors> findNames() {
		return vendorsRepository.findNames();
	}

	@Override
	public Vendors save(Vendors vendors) {
		vendors.setCategory(categoriesRepository.findById(vendors.getCategory().getId()));
		vendors.setSubCategory(subCategoriesRepository.findById(vendors.getSubCategory().getId()));
		vendors.setClassRange(classRangeRepository.findById(vendors.getClassRange().getId()));
		vendors.setServices(vendors.getServices().stream()
				.map(item -> servicesRepository.findById(item.getId())).collect(Collectors.toList()));
		return vendorsRepository.save(vendors);
	}

	@Override
	public void delete(Integer id) {
		vendorsRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		vendorsRepository.deleteAll();
	}

	@Override
	public Vendors findById(Integer id) {
		return vendorsRepository.findById(id);
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
		for(Integer id:services) {
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

}
