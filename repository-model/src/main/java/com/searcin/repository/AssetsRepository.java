package com.searcin.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.Repository;

import com.searcin.entity.Assets;

@Transactional
public interface AssetsRepository extends Repository<Assets, Integer> {
	
	Assets save(Assets asset);

	Assets findById(Integer id);

	void delete(Assets asset);

	void deleteById(Integer id);

	List<Assets> findAll();
}
