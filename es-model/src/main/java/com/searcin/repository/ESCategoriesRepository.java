package com.searcin.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.searcin.document.ESCategories;

public interface ESCategoriesRepository extends ElasticsearchRepository<ESCategories, Integer> {

}
