package com.searcin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.searcin.entity.Users;
import com.searcin.repository.UsersRepository;
import com.searcin.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;
	
	public boolean isExist(String username) {
		return usersRepository.findNames().stream()
				.anyMatch(item -> item.getUsername().contains(username));
	}

	@Override
	public void save(Users user) {
		usersRepository.save(user);
	}

	@Override
	public List<Users> findAll() {
		return usersRepository.findAll();
	}

	@Override
	public Users findByUsername(String username) {
		return usersRepository.findOneByUsername(username);
	}

}
