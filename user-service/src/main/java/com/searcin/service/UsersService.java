package com.searcin.service;

import java.util.List;

import com.searcin.entity.Users;

public interface UsersService {

	void saveUser(Users user);
	
	List<Users> findAll();
	
	boolean isExist(String username);
	
	Users findByUsername(String username);

	Users findByUsernameAndPassword(String username, String password);
	
	String getToken(String username, List<String> roles);
}
