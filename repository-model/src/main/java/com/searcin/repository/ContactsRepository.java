package com.searcin.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.Repository;

import com.searcin.entity.Contacts;

@Transactional
public interface ContactsRepository extends Repository<Contacts, Long> {

	List<Contacts> findAll();

	Contacts save(Contacts contact);

	//Contacts findByVendor_id(Integer id);

}
