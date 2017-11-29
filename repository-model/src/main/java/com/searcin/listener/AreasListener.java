package com.searcin.listener;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.transaction.Transactional;

import com.searcin.entity.Areas;
import com.searcin.mapper.ESMapper;
import com.searcin.service.BeanUtil;
import com.searcin.service.IngestionService;

public class AreasListener {
	
	@PostPersist
	public void persist(Areas area) {
		persistES(area);
	}
	
	@PostUpdate
	public void update(Areas area) {
		persistES(area);
	}
	
	@PostRemove
	public void delete(Areas area) {
		removeES(area);
	}
	
	@Transactional 
	public void persistES(Areas area) {
		BeanUtil.getBean(IngestionService.class)
				.save(BeanUtil.getBean(ESMapper.class).toES(area));
	}
	
	@Transactional 
	public void removeES(Areas area) {
		BeanUtil.getBean(IngestionService.class)
				.delete(BeanUtil.getBean(ESMapper.class).toES(area));
	}

}
