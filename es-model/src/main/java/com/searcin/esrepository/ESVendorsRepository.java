package com.searcin.esrepository;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.searcin.document.ESVendors;

public interface ESVendorsRepository extends ElasticsearchRepository<ESVendors, Integer> {
	
	@Query("{\"query\":{\"term\":{\"address.area.id\":?0}}}")
	Iterable<ESVendors> findByAreaId(Integer id);

	@Query("{\"query\":{\"term\":{\"address.id\":?0}}}")
	Iterable<ESVendors> findByAddressId(Integer id);

	@Query("{\"query\":{\"term\":{\"category.id\":?0}}}")
	Iterable<ESVendors> findByCategoryId(Integer id);

	@Query("{\"query\":{\"term\":{\"services.id\":?0}}}")
	Iterable<ESVendors> findByServiceId(Integer id);
}
