package com.searcin.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.searcin.view.Dto;

public class UsersDto {
	@JsonView(Dto.User.id.class)
	private String id;
	@JsonView(Dto.User.name.class)
	private String name;
	@JsonView(Dto.User.auth.class)
	private String username;
	@JsonView(Dto.User.auth.class)
	private String password;
	@JsonView(Dto.User.roles.class)
	private List<String> roles;
	@JsonView(Dto.User.token.class)
	private String token;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
