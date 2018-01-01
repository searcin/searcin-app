package com.searcin.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.searcin.document.ESAreas;

public interface ESAreasRepository extends ElasticsearchRepository<ESAreas, Integer> {
}
