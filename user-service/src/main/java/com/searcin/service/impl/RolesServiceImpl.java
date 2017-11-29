package com.searcin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.searcin.entity.Roles;
import com.searcin.repository.RolesRepository;
import com.searcin.service.RolesService;

@Service
public class RolesServiceImpl implements RolesService {
	
	@Autowired
	private RolesRepository rolesRepository;
	
	public List<Roles> findAll() {
		return rolesRepository.findAll();
	}
}
