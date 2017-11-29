package com.searcin.service;

import java.util.List;

import com.searcin.entity.Contacts;

public interface ContactsService {

	List<Contacts> findAll();

	Contacts save(Contacts contact);

	Contacts findByVendor(Integer id);

}
