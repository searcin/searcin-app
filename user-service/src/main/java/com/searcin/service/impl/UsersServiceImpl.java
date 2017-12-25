package com.searcin.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.searcin.constant.RoleType;
import com.searcin.entity.Users;
import com.searcin.exception.UnauthorizedException;
import com.searcin.exception.UserNotFoundException;
import com.searcin.repository.RolesRepository;
import com.searcin.repository.UsersRepository;
import com.searcin.service.UsersService;
import com.searcin.utils.TokenGenerator;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private RolesRepository rolesRepository;
	
	@Autowired
	private TokenGenerator tokenGenerator;
	
	public boolean isExist(String username) {
		return usersRepository.findNames().stream()
				.anyMatch(item -> item.getUsername().contains(username));
	}

	@Override
	public void saveUser(Users user) {
		user.setRoles(Arrays.asList(rolesRepository.findById(RoleType.USER.getId())));
		usersRepository.save(user);
	}

	@Override
	public List<Users> findAll() {
		return usersRepository.findAll();
	}

	@Override
	public Users findByUsername(String username) {
		return Optional.ofNullable(usersRepository.findOneByUsername(username))
					.orElseThrow(() -> new UserNotFoundException("User not found!"));
	}
		
	@Override
	public Users findByUsernameAndPassword(String username, String password) {
		return Optional.ofNullable(usersRepository.findByUsernameAndPassword(username, password))
			.orElseThrow(() -> new UnauthorizedException("Username/password must be wrong!"));
	}
	
	public String getToken(String username, List<String> roles) {
		return tokenGenerator.getToken(username, roles);
	}

}
