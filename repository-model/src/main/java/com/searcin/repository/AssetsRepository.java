package com.searcin.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.Repository;

import com.searcin.entity.Assets;

@Transactional
public interface AssetsRepository extends Repository<Assets, Integer> {
	
	void save(Assets asset);

	Assets findById(Integer id);

	void delete(Assets asset);

	void deleteById(Integer id);
}
