package com.searcin.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.searcin.constant.AssetType;
import com.searcin.document.ESAddresses;
import com.searcin.document.ESAreas;
import com.searcin.document.ESCategories;
import com.searcin.document.ESContacts;
import com.searcin.document.ESServices;
import com.searcin.document.ESSubCategories;
import com.searcin.document.ESVendors;
import com.searcin.entity.Addresses;
import com.searcin.entity.Areas;
import com.searcin.entity.Categories;
import com.searcin.entity.Contacts;
import com.searcin.entity.Services;
import com.searcin.entity.SubCategories;
import com.searcin.entity.Assets;
import com.searcin.entity.Vendors;

@Component
public class ESMapper {

	@Autowired
	private ModelMapper modelMapper;

	public ESServices toES(Services service) {
		return modelMapper.map(service, ESServices.class);
	}

	public ESCategories toES(Categories category) {
		return modelMapper.map(category, ESCategories.class);
	}

	public ESAreas toES(Areas area) {
		return modelMapper.map(area, ESAreas.class);
	}

	public ESSubCategories toES(SubCategories subCategory) {
		ESSubCategories esSubCategory = modelMapper.map(subCategory, ESSubCategories.class);
		esSubCategory.setCategoryId(subCategory.getCategory().getId());
		return esSubCategory;
	}

	public ESVendors toES(Vendors vendor) {
		ESVendors esVendor = new ESVendors();
		new ModelMapper().createTypeMap(Vendors.class, ESVendors.class)
				.addMappings(mapper -> mapper.skip(ESVendors::setLogo))
				.addMappings(mapper -> mapper.skip(ESVendors::setGallery))
				.addMappings(mapper -> mapper.skip(ESVendors::setAddress))
				.addMappings(mapper -> mapper.skip(ESVendors::setContact)).map(vendor, esVendor);		
		String logo = null;
		List<String> gallery = new ArrayList<>();		
		List<Assets> assets = vendor.getAssets();		
		if(assets != null) {
			logo = assets.stream().filter(item -> item.getType().equals(AssetType.VENDORLOGO.getValue()))
					.findFirst().map(item -> item.getMetadata()).orElse(null);
			gallery = assets.stream().filter(item -> item.getType().equals(AssetType.VENDORGALLERY.getValue()))
					.map(item -> item.getMetadata()).collect(Collectors.toList());
		}		
		esVendor.setLogo(logo);
		esVendor.setGallery(gallery);
		esVendor.setAddress(toESAddress(vendor));
		esVendor.setContact(toESContact(vendor));
		return esVendor;
	}

	private ESAddresses toESAddress(Vendors vendor) {
		return Optional.ofNullable(vendor.getAddress()).map(address -> {
			ESAddresses esAddress = new ESAddresses();
			new ModelMapper().createTypeMap(Addresses.class, ESAddresses.class)
					.addMappings(mapper -> mapper.skip(ESAddresses::setLocation)).map(address, esAddress);
			Map<String, Double> location = new HashMap<>();
			location.put("lat", address.getLat());
			location.put("lon", address.getLng());
			esAddress.setLocation(location);
			return esAddress;
		}).orElse(null);
	}

	private ESContacts toESContact(Vendors vendor) {
		return Optional.ofNullable(vendor.getContact()).map(contact -> modelMapper.map(contact, ESContacts.class))
				.orElse(null);
	}

	public ESAddresses toES(Addresses address) {
		ESAddresses esAddress = new ESAddresses();
		new ModelMapper().createTypeMap(Addresses.class, ESAddresses.class)
				.addMappings(mapper -> mapper.skip(ESAddresses::setLocation)).map(address, esAddress);
		Map<String, Double> location = new HashMap<>();
		location.put("lat", address.getLat());
		location.put("lon", address.getLng());
		esAddress.setLocation(location);
		return esAddress;
	}
	
	public ESContacts toES(Contacts contact) {
		return modelMapper.map(contact, ESContacts.class);
	}
}
