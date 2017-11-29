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

import com.searcin.constant.ESSuggest;
import com.searcin.constant.ESType;
import com.searcin.document.ESAreas;
import com.searcin.document.ESCategories;
import com.searcin.document.ESServices;
import com.searcin.document.ESSubCategories;
import com.searcin.document.ESVendors;
import com.searcin.esrepository.ESAreasRepository;
import com.searcin.esrepository.ESCategoriesRepository;
import com.searcin.esrepository.ESServicesRepository;
import com.searcin.esrepository.ESSubCategoriesRepository;
import com.searcin.esrepository.ESVendorsRepository;
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
		for (ESSuggest suggest : ESSuggest.values()) {
			query.should(QueryBuilders.matchQuery("_type", ESType.valueOf(suggest.toString()).getName())
					.boost(suggest.getBoost()));
		}
		query.must(QueryBuilders.matchPhrasePrefixQuery("name.key", qs));
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(query).withIndices(indexName)
				.withTypes(ESType.AREAS.getName(), ESType.CATEGORIES.getName(), ESType.SUBCATEGORIES.getName(),
						ESType.SERVICES.getName(), ESType.VENDORS.getName())
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
}
