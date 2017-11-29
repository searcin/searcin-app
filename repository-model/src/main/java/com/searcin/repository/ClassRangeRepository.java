package com.searcin.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.searcin.entity.ClassRange;

public interface ClassRangeRepository extends Repository<ClassRange, Integer>{

	List<ClassRange> findAll();

	ClassRange findById(Integer id);

}
