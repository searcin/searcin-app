package com.searcin.listener;

import java.util.Optional;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import com.searcin.document.ESServices;
import com.searcin.entity.Services;
import com.searcin.mapper.ESMapper;
import com.searcin.service.BeanUtil;
import com.searcin.service.IngestionService;

public class ServicesListener {

	@PrePersist
	@PreUpdate
	public void active(Services service) {
		service.setIsActive(Optional.ofNullable(service.getIsActive()).orElse(true));
	}

	@PostPersist
	@PostUpdate
	public void save(Services service) {
		if (service.getIsActive())
			persist(BeanUtil.getBean(ESMapper.class).toES(service));
		else
			delete(BeanUtil.getBean(ESMapper.class).toES(service));
	}

	@Transactional
	private void persist(ESServices esService) {
		BeanUtil.getBean(IngestionService.class).save(esService);
	}

	@Transactional
	private void delete(ESServices esService) {
		BeanUtil.getBean(IngestionService.class).delete(esService);
	}
}
