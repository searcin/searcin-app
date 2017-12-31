package com.searcin.service;

import java.util.List;
import java.util.Map;

import com.searcin.document.ESAreas;
import com.searcin.document.ESCategories;
import com.searcin.document.ESServices;
import com.searcin.document.ESSubCategories;
import com.searcin.document.ESVendors;

public interface SearchService {

	List<Map<String, Object>> suggest(String qs);

	ESVendors findVendor(Integer id);

	ESCategories findCategoryById(Integer id);

	ESSubCategories findSubCategoryById(Integer id);

	ESServices findServiceById(Integer id);

	ESAreas findAreaById(Integer areaId);

	List<ESCategories> findCategories();
}
