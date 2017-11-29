package com.searcin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.searcin.dto.AuthDto;
import com.searcin.dto.UsersDto;
import com.searcin.entity.Users;
import com.searcin.exception.InvalidConstraintException;
import com.searcin.exception.NotFoundException;
import com.searcin.exception.UnauthorizedException;
import com.searcin.service.UsersService;
import com.searcin.utils.DataConverter;
import com.searcin.utils.JWTParser;

@RestController
@RequestMapping("/user")
public class UserController {

	private final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UsersService usersService;

	@Autowired
	private DataConverter convert;

	@Autowired
	private JWTParser parser;

	@RequestMapping("/register")
	public void register(@RequestBody UsersDto user) {
		if (usersService.isExist(user.getUsername())) {
			throw new InvalidConstraintException("Username already exists!");
		} else {
			usersService.save(convert.toUserEntity(user));
		}
	}
	
	//by user name
	@RequestMapping("/details")
	public UsersDto details(@RequestParam String username) {
		return convert.toUserDto(usersService.findByUsername(username));
	}

	@RequestMapping("/login")
	public AuthDto login(@RequestParam String username, @RequestParam String password) {
		Users user = usersService.findByUsername(username);
		List<String> roles = new ArrayList<>();
		AuthDto auth = new AuthDto();
		if (user != null) {
			if (user.getPassword().equals(password)) {
				roles = user.getRoles().stream().map(item -> item.getName()).collect(Collectors.toList());
				auth.setName(user.getName());
				auth.setRoles(roles);
				auth.setToken(parser.getToken(username, roles));
			} else {
				throw new UnauthorizedException("Password didn't match with username!");
			}
		} else {
			throw new NotFoundException("Username not found!");
		}
		return auth;
	}
	
	@RequestMapping("/password/change")
	public void changePassword(@RequestBody UsersDto userDto) {
		Users user = usersService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		user.setPassword(userDto.getPassword());
		log.info("Changing the password");
		usersService.save(user);
	}
}
