package com.searcin.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.searcin.constant.AssetType;
import com.searcin.document.ESVendors;
import com.searcin.entity.Areas;
import com.searcin.entity.Categories;
import com.searcin.entity.Services;
import com.searcin.entity.SubCategories;
import com.searcin.entity.Vendors;
import com.searcin.mapper.ESMapper;
import com.searcin.service.AreasService;
import com.searcin.service.AssetsService;
import com.searcin.service.CategoriesService;
import com.searcin.service.IngestionService;
import com.searcin.service.ServicesService;
import com.searcin.service.SubCategoriesService;
import com.searcin.service.VendorsService;

@RestController
@RequestMapping("/ingest")
public class IngestionController {	
	
	@Autowired
	private ESMapper esMapper;
	
	@Autowired
	private IngestionService ingestionService;
	
	@Autowired
	private AreasService areasService;

	@Autowired
	private CategoriesService categoriesService;

	@Autowired
	private ServicesService servicesService;

	@Autowired
	private SubCategoriesService subCategoriesService;

	@Autowired
	private VendorsService vendorsService;
	

	@Autowired
	private AssetsService assetsService;
	
	@RequestMapping(value = "/synces")
	public void esPush() {
		
		ingestionService.reset();
		
		List<Services> services = servicesService.findAll();		
		if(services != null && services.size() > 0) {
			ingestionService.service(services.stream()
					.map(item -> esMapper.toES(item)).collect(Collectors.toList()));
		}		
		
		List<Areas> areas = areasService.findAll();		
		if(areas != null && areas.size() > 0) {
			ingestionService.area(areas.stream()
					.map(item -> esMapper.toES(item)).collect(Collectors.toList()));			
		}
		
		List<SubCategories> subCategories = subCategoriesService.findAll();
		if(subCategories != null && subCategories.size() > 0) {
			ingestionService.subcategory(subCategories.stream()
					.map(item -> esMapper.toES(item)).collect(Collectors.toList()));
		}		
		
		List<Categories> categories = categoriesService.findAll();
		if(categories != null && categories.size() > 0) {
			ingestionService.category(categories.stream()
					.map(item -> esMapper.toES(item)).collect(Collectors.toList()));
		}			
		
		List<Vendors> vendors = vendorsService.findAll();		
		if(vendors != null && vendors.size() > 0) {
			ingestionService.vendor(vendors.stream()
					.map(item -> {
					ESVendors esVendor = esMapper.toES(item);
					esVendor.setLogo(assetsService.list(item.getId(), AssetType.LOGO.getValue()).stream()
						.map(asset -> assetsService.getHost() + asset.getKey())
						.collect(Collectors.toList()));
					esVendor.setGallery(assetsService.list(item.getId(), AssetType.GALLERY.getValue()).stream()
							.map(asset -> assetsService.getHost() + asset.getKey())
							.collect(Collectors.toList()));
					return esVendor;
					}).collect(Collectors.toList()));
		}		
	}
}
