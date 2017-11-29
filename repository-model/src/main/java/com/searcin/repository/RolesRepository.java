package com.searcin.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.Repository;

import com.searcin.entity.Roles;

@Transactional
public interface RolesRepository extends Repository<Roles, Integer> {
	public List<Roles> findAll();
}
