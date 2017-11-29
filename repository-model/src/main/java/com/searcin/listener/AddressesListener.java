package com.searcin.listener;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.transaction.Transactional;

import com.searcin.document.ESAddresses;
import com.searcin.entity.Addresses;
import com.searcin.mapper.ESMapper;
import com.searcin.service.BeanUtil;
import com.searcin.service.IngestionService;

public class AddressesListener {
	
	@PostPersist
	@PostUpdate
	public void save(Addresses address) {
		save(BeanUtil.getBean(ESMapper.class).toES(address), address.getVendor().getId());
	}
	
	@PostRemove
	public void remove(Addresses address) {
		//nothing to do
	}
	
	@Transactional
	private void save(ESAddresses address, Integer id) {
		BeanUtil.getBean(IngestionService.class).save(address, id);
	}
}
