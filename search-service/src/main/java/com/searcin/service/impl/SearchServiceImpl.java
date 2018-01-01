package com.searcin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.searcin.constant.DocumentType;
import com.searcin.document.ESAreas;
import com.searcin.document.ESCategories;
import com.searcin.document.ESServices;
import com.searcin.document.ESSubCategories;
import com.searcin.document.ESVendors;
import com.searcin.repository.ESAreasRepository;
import com.searcin.repository.ESCategoriesRepository;
import com.searcin.repository.ESServicesRepository;
import com.searcin.repository.ESSubCategoriesRepository;
import com.searcin.repository.ESVendorsRepository;
import com.searcin.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

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
	public List<Map<String, Object>> suggest(String qs) {
		BoolQueryBuilder query = QueryBuilders.boolQuery();		
		query.should(QueryBuilders.matchQuery("_type", DocumentType.VENDORS.getName())).boost(1.0f);
		query.must(QueryBuilders.matchPhrasePrefixQuery("name.key", qs));
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(query).withIndices(indexName)
				.build();		
		return elasticsearchTemplate.query(searchQuery, new ResultsExtractor<List<Map<String, Object>>>() {
			@Override
			public List<Map<String, Object>> extract(SearchResponse response) {
				List<Map<String, Object>> result = new ArrayList<>();
				response.getHits().forEach(item -> {
					Map<String, Object> resultObj = new HashMap<>();
					resultObj.put("type", item.getType());
					resultObj.put("source", item.getSource());
					result.add(resultObj);
				});
				return result;
			}
		});
	}

	@Override
	public ESVendors findVendor(Integer id) {
		return esVendorsRepository.findOne(id);
	}

	@Override
	public ESCategories findCategoryById(Integer id) {
		return esCategoriesRepository.findOne(id);
	}

	@Override
	public ESSubCategories findSubCategoryById(Integer id) {
		return esSubCategoriesRepository.findOne(id);
	}

	@Override
	public ESServices findServiceById(Integer id) {
		return esServicesRepository.findOne(id);
	}

	@Override
	public ESAreas findAreaById(Integer id) {
		return esAreasRepository.findOne(id);
	}

	@Override
	public List<ESCategories> findCategories() {
		List<ESCategories> esCategories = new ArrayList<>();
		esCategoriesRepository.findAll().forEach(esCategories::add);
		return esCategories;
	}
}
