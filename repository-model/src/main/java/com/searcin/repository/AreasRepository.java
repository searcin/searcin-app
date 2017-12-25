package com.searcin.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.searcin.entity.Areas;

@Transactional
public interface AreasRepository extends Repository<Areas, Long> {
	
	Areas findById(Integer id);

	@Query("select new Areas(a.id,a.name) from Areas a where a.isActive = true")
	List<Areas> findNames();

	@Query("select a from Areas a where a.isActive = true")
	List<Areas> findAll();

	Page<Areas> findAll(Pageable page);

	Areas save(Areas areas);

	void delete(Areas areas);

	void deleteAll();
}
