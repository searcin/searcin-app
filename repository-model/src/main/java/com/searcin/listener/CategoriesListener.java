package com.searcin.listener;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

import com.searcin.document.ESCategories;
import com.searcin.entity.Categories;
import com.searcin.mapper.ESMapper;
import com.searcin.service.BeanUtil;
import com.searcin.service.IngestionService;

public class CategoriesListener {
	
	@PostPersist
	@PostUpdate
	public void persist(Categories category) {
		persist(BeanUtil.getBean(ESMapper.class).toES(category));
	}
	
	private void persist(ESCategories es) {
		BeanUtil.getBean(IngestionService.class).save(es);
	}
	
	@PostRemove
	public void delete(Categories category) {
		delete(BeanUtil.getBean(ESMapper.class).toES(category));
	}

	private void delete(ESCategories es) {
		BeanUtil.getBean(IngestionService.class).delete(es);
	}
}
