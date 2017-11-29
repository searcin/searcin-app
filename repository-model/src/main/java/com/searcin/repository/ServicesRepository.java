package com.searcin.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.searcin.entity.Services;

@Transactional
public interface ServicesRepository extends Repository<Services, Long>{

	@Query("select new Services(s.id,s.name,s.description) from Services s")
	List<Services> findAll();
	
	@Query("select new Services(s.id,s.name) from Services s")
	List<Services> findNames();

	Services save(Services service);

	Services findById(Integer id);

	Object delete(Services service);

	Page<Services> findAll(Pageable pageable);

}
