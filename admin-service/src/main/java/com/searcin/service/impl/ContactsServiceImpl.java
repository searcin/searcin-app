package com.searcin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.searcin.entity.Contacts;
import com.searcin.repository.ContactsRepository;
import com.searcin.service.ContactsService;

@Service
public class ContactsServiceImpl implements ContactsService {
	
	@Autowired
	private ContactsRepository contactsRepository;

	@Override
	public List<Contacts> findAll() {
		return contactsRepository.findAll();
	}

	@Override
	public Contacts save(Contacts contact) {
		return contactsRepository.save(contact);
	}

	@Override
	public Contacts findByVendor(Integer id) {
		return contactsRepository.findByVendor_id(id);
	}

}
