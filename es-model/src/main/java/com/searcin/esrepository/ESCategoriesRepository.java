package com.searcin.esrepository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.searcin.document.ESCategories;

public interface ESCategoriesRepository extends ElasticsearchRepository<ESCategories, Integer> {

}
