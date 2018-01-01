package com.searcin.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.searcin.document.ESServices;

public interface ESServicesRepository extends ElasticsearchRepository<ESServices, Integer> {

}
