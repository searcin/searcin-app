package com.searcin.service;

import java.util.List;

import com.searcin.document.ESAddresses;
import com.searcin.document.ESAreas;
import com.searcin.document.ESCategories;
import com.searcin.document.ESContacts;
import com.searcin.document.ESServices;
import com.searcin.document.ESSubCategories;
import com.searcin.document.ESVendors;

public interface IngestionService {

	void category(List<ESCategories> categories);

	void service(List<ESServices> services);
	
	void subcategory(List<ESSubCategories> subcategories);
	
	void area(List<ESAreas> areas);
	
	void vendor(List<ESVendors> vendors);

	void reset();
	
	void save(ESAreas area);

	void delete(ESAreas area);

	void save(ESAddresses address, Integer id);

	void save(ESCategories es);

	void delete(ESCategories es);

	void save(ESSubCategories es);

	void delete(ESSubCategories es);

	void save(ESContacts contact, Integer id);

	void save(ESServices service);

	void delete(ESServices service);

	void save(ESVendors vendor);

	void delete(ESVendors esVendor);

	void update(ESVendors vendor);

	void save(List<String> collect, String type, Integer id);

}
