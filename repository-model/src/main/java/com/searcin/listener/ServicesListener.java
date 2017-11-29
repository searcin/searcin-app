package com.searcin.listener;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

import com.searcin.document.ESServices;
import com.searcin.entity.Services;
import com.searcin.mapper.ESMapper;
import com.searcin.service.BeanUtil;
import com.searcin.service.IngestionService;

public class ServicesListener {
	@PostPersist
	@PostUpdate
	public void save(Services service) {
		save(BeanUtil.getBean(ESMapper.class).toES(service));
	}
	
	private void save(ESServices service) {
		BeanUtil.getBean(IngestionService.class).save(service);
	}
	
	@PostRemove
	public void delete(Services service) {
		delete(BeanUtil.getBean(ESMapper.class).toES(service));
	}

	private void delete(ESServices service) {
		BeanUtil.getBean(IngestionService.class).delete(service);
	}
}
