package com.searcin.esrepository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.searcin.document.ESServices;

public interface ESServicesRepository extends ElasticsearchRepository<ESServices, Integer> {

}
