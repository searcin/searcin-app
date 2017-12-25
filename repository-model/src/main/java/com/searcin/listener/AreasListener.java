package com.searcin.listener;

import java.util.Optional;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import com.searcin.document.ESAreas;
import com.searcin.entity.Areas;
import com.searcin.mapper.ESMapper;
import com.searcin.service.BeanUtil;
import com.searcin.service.IngestionService;

public class AreasListener {

	@PrePersist
	@PreUpdate
	public void active(Areas area) {
		//if null set to active
		area.setIsActive(Optional.ofNullable(area.getIsActive()).orElse(true));
	}

	@PostPersist
	@PostUpdate
	public void save(Areas area) {
		if (area.getIsActive()) // if active add to es
			save(BeanUtil.getBean(ESMapper.class).toES(area));
		else // if inactive remove from es
			remove(BeanUtil.getBean(ESMapper.class).toES(area));
	}

	@Transactional
	private void save(ESAreas esArea) {
		BeanUtil.getBean(IngestionService.class).save(esArea);
	}

	@Transactional
	private void remove(ESAreas esArea) {
		BeanUtil.getBean(IngestionService.class).delete(esArea);
	}

}
