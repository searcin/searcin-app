package com.searcin.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.searcin.entity.Services;

@Transactional
public interface ServicesRepository extends Repository<Services, Long> {

	@Query("select s from Services s where s.isActive = true")
	List<Services> findAll();

	@Query("select new Services(s.id,s.name) from Services s where s.isActive = true")
	List<Services> findNames();

	Services findById(Integer id);

	Services save(Services service);

	void delete(Services service);

	void deleteAll();

	Page<Services> findAll(Pageable pageable);

}
