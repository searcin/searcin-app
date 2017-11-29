package com.searcin.service;

import java.util.List;

import com.searcin.entity.Users;

public interface UsersService {

	void save(Users user);
	
	List<Users> findAll();
	
	boolean isExist(String username);
	
	Users findByUsername(String username);

}
