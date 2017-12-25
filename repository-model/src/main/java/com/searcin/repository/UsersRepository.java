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
	
	@Query("select u from Users u where u.username = ?1 and u.password = ?2")
	Users findByUsernameAndPassword(String username, String password);
}
