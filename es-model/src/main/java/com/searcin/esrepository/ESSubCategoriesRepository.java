package com.searcin.esrepository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.searcin.document.ESSubCategories;

public interface ESSubCategoriesRepository extends ElasticsearchRepository<ESSubCategories, Integer> {

}
