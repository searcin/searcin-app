package com.searcin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.searcin.dto.UsersDto;
import com.searcin.entity.Users;
import com.searcin.exception.InvalidConstraintException;
import com.searcin.exception.ValidationException;
import com.searcin.service.UsersService;
import com.searcin.utils.DataConverter;
import com.searcin.view.Views;

@RestController
@RequestMapping("/user")
public class UserController {

	private final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UsersService usersService;

	@Autowired
	private DataConverter convert;

	@RequestMapping("/register")
	public void register(@RequestBody UsersDto user) {
		if (usersService.isExist(user.getUsername())) {
			throw new InvalidConstraintException("Username already exists!");
		} else {
			usersService.saveUser(convert.toEntity(user));
		}
	}

	@JsonView(Views.UserBasic.class)
	@RequestMapping("/login")
	public UsersDto login(@RequestParam String username, @RequestParam String password) {
		UsersDto userDto;
		if(username != null && username != "" && password != null && password != "") {
			userDto = convert.toDto(usersService.findByUsernameAndPassword(username, password));
			userDto.setToken(usersService.getToken(username, userDto.getRoles()));
			return userDto;
		}
		else {
			throw new ValidationException("Username/Password cannot be empty!");
		}
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping("/password/change")
	public void changePassword(@RequestBody UsersDto userDto) {
		Users user = usersService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		user.setPassword(userDto.getPassword());
		log.info("Changing the password");
		usersService.saveUser(user);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@RequestMapping("/details")
	public UsersDto detail(@RequestParam String username) {
		return convert.toDto(usersService.findByUsername(username));
	}
	
	
}
