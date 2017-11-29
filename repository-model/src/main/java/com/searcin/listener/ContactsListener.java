package com.searcin.listener;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

import com.searcin.document.ESContacts;
import com.searcin.entity.Contacts;
import com.searcin.mapper.ESMapper;
import com.searcin.service.BeanUtil;
import com.searcin.service.IngestionService;

public class ContactsListener {
	@PostPersist
	@PostUpdate
	public void persist(Contacts contact) {
		persist(BeanUtil.getBean(ESMapper.class).toES(contact), contact.getVendor().getId());
	}
	
	private void persist(ESContacts contact, Integer id) {
		BeanUtil.getBean(IngestionService.class).save(contact, id);
	}
	
	@PostRemove
	public void delete(Contacts contact) {
		
	}
}
