package com.searcin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.searcin.entity.ClassRange;
import com.searcin.repository.ClassRangeRepository;
import com.searcin.service.ClassRangeService;

@Service
public class ClassRangeServiceImpl implements ClassRangeService {
	
	@Autowired
	private ClassRangeRepository classRangeRepository;
	
	public List<ClassRange> findAll() {
		return classRangeRepository.findAll();
	}
}
