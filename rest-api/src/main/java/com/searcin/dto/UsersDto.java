package com.searcin.dto;

import java.util.List;

public class UsersDto {
	private String id;
	private String name;
	private String username;
	private String password;
	private List<NameDto> roles;

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

	public List<NameDto> getRoles() {
		return roles;
	}

	public void setRoles(List<NameDto> roles) {
		this.roles = roles;
	}
}
