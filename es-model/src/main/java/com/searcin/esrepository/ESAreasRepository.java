package com.searcin.esrepository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.searcin.document.ESAreas;

public interface ESAreasRepository extends ElasticsearchRepository<ESAreas, Integer> {
}
