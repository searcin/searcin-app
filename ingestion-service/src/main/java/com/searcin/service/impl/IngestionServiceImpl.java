package com.searcin.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

import com.searcin.constant.ESVendorFields;
import com.searcin.document.ESAddresses;
import com.searcin.document.ESAreas;
import com.searcin.document.ESCategories;
import com.searcin.document.ESContacts;
import com.searcin.document.ESNested;
import com.searcin.document.ESServices;
import com.searcin.document.ESSubCategories;
import com.searcin.document.ESVendors;
import com.searcin.esrepository.ESAreasRepository;
import com.searcin.esrepository.ESCategoriesRepository;
import com.searcin.esrepository.ESServicesRepository;
import com.searcin.esrepository.ESSubCategoriesRepository;
import com.searcin.esrepository.ESVendorsRepository;
import com.searcin.service.IngestionService;

@Service
public class IngestionServiceImpl implements IngestionService {

	@Value("${elasticsearch.index}")
	private String indexName;

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	private ESAreasRepository esAreasRepository;

	@Autowired
	private ESCategoriesRepository esCategoriesRepository;

	@Autowired
	private ESSubCategoriesRepository esSubCategoriesRepository;

	@Autowired
	private ESVendorsRepository esVendorsRepository;
	
	@Autowired
	private ESServicesRepository esServicesRepository;

	@Override
	public void vendor(List<ESVendors> vendor) {
		elasticsearchTemplate.bulkIndex(vendor.stream()
				.map(item -> new IndexQueryBuilder().withId(item.getId().toString()).withObject(item).build())
				.collect(Collectors.toList()));
	}

	@Override
	public void category(List<ESCategories> categories) {
		elasticsearchTemplate.bulkIndex(categories.stream()
				.map(item -> new IndexQueryBuilder().withId(item.getId().toString()).withObject(item).build())
				.collect(Collectors.toList()));
	}

	@Override
	public void service(List<ESServices> services) {
		services.forEach(item -> save(item));
		elasticsearchTemplate.bulkIndex(services.stream()
				.map(item -> new IndexQueryBuilder().withId(item.getId().toString()).withObject(item).build())
				.collect(Collectors.toList()));
	}

	@Override
	public void subcategory(List<ESSubCategories> subcategories) {
		elasticsearchTemplate.bulkIndex(subcategories.stream()
				.map(item -> new IndexQueryBuilder().withId(item.getId().toString()).withObject(item).build())
				.collect(Collectors.toList()));
	}

	@Override
	public void area(List<ESAreas> areas) {
		elasticsearchTemplate.bulkIndex(areas.stream()
				.map(item -> new IndexQueryBuilder().withId(item.getId().toString()).withObject(item).build())
				.collect(Collectors.toList()));
	}

	@Override
	public void reset() {
		// delete the existing index
		elasticsearchTemplate.deleteIndex(indexName);
		
		// case insensitive analyzer
		Settings.Builder settings = Settings.builder();
		settings.put("analysis.analyzer.case_insensitive.tokenizer","keyword");
		settings.put("analysis.analyzer.case_insensitive.filter", "lowercase,uppercase");
		Map<String, String> expectedSettingsMap = settings.build().getAsMap();
		
		// create the index
		elasticsearchTemplate.createIndex(indexName, expectedSettingsMap);
		
		
		// put mapping
		elasticsearchTemplate.putMapping(ESServices.class);
		elasticsearchTemplate.putMapping(ESAreas.class);
		elasticsearchTemplate.putMapping(ESSubCategories.class);
		elasticsearchTemplate.putMapping(ESCategories.class);
		elasticsearchTemplate.putMapping(ESVendors.class);
	}

	@Override
	public void save(ESAreas esarea) {
		// save area
		esAreasRepository.save(esarea);

		// save vendor
		esVendorsRepository.findByAreaId(esarea.getId()).forEach(item -> {
			item.getAddress().setArea(esarea.getId(), esarea.getName());
			esVendorsRepository.save(item);
		});
	}

	@Override
	public void delete(ESAreas area) {
		esAreasRepository.delete(area);
	}
	
	@Override
	public void save(ESAddresses address, Integer id) {
		ESVendors vendor = esVendorsRepository.findOne(id);
		vendor.setAddress(address);
		esVendorsRepository.save(vendor);
	}

	@Override
	public void save(ESCategories es) {
		// save category
		esCategoriesRepository.save(es);
		// save vendor
		esVendorsRepository.findByCategoryId(es.getId()).forEach(item -> {
			item.setCategory(new ESNested(es.getId(), es.getName()));
			esVendorsRepository.save(item);
		});
	}

	@Override
	public void delete(ESCategories es) {
		esCategoriesRepository.delete(es);
	}

	@Override
	public void save(ESSubCategories es) {
		esSubCategoriesRepository.save(es);
	}

	@Override
	public void delete(ESSubCategories es) {
		esSubCategoriesRepository.delete(es);
	}

	@Override
	public void save(ESContacts contact, Integer id) {
		ESVendors vendor = esVendorsRepository.findOne(id);
		vendor.setContact(contact);
		esVendorsRepository.save(vendor);
	}

	@Override
	public void save(ESServices esService) {
		esServicesRepository.save(esService);
		esVendorsRepository.findByServiceId(esService.getId()).forEach(item -> {
			item.setServices(item.getServices().stream()
								.map(service -> {
									
									if(esService.getId() == service.getId())
										service.setName(esService.getName());
									
									return service;
								})
								.collect(Collectors.toList()));
			esVendorsRepository.save(item);
		});
	}

	@Override
	public void delete(ESServices service) {
		esServicesRepository.delete(service);
	}

	@Override
	public void save(ESVendors vendor) {
		esVendorsRepository.save(vendor);
	}

	@Override
	public void delete(ESVendors esVendor) {
		esVendorsRepository.delete(esVendor);
	}

	@Override
	public void update(ESVendors esVendor) {
		ESVendors vendor = new ESVendors();
		BeanUtils.copyProperties(esVendor, vendor, ESVendorFields.ADDRESS.getField(), 
				ESVendorFields.CONTACT.getField(),ESVendorFields.GALLERY.getField(),ESVendorFields.LOGO.getField());
		esVendorsRepository.save(vendor);
	}

	@Override
	public void save(List<String> collect, String type, Integer id) {
		ESVendors esVendor = esVendorsRepository.findOne(id);
		if(type.equals(ESVendorFields.LOGO.getField())) {			
			esVendor.setLogo(collect);
		}
		else if(type.equals(ESVendorFields.GALLERY.getField())) {
			esVendor.setGallery(collect);
		}
		else {
			System.out.println("Not a valid type");
		}
		esVendorsRepository.save(esVendor);
	}
	
}
