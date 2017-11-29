package com.searcin.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.searcin.entity.Users;

@Transactional
public interface UsersRepository extends Repository<Users, Long> {

	void save(Users user);
	
	List<Users> findAll();
	
	@Query("select new Users(u.username) from Users u")
	List<Users> findNames();

	Users findOneByUsername(String username);
}
