package com.searcin.listener;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

import com.searcin.document.ESSubCategories;
import com.searcin.entity.SubCategories;
import com.searcin.mapper.ESMapper;
import com.searcin.service.BeanUtil;
import com.searcin.service.IngestionService;

public class SubCategoriesListener {
	@PostPersist
	@PostUpdate
	public void save(SubCategories subCategory) {
		save(BeanUtil.getBean(ESMapper.class).toES(subCategory));
	}
	
	private void save(ESSubCategories es) {
		BeanUtil.getBean(IngestionService.class).save(es);
	}
	
	@PostRemove
	public void delete(SubCategories subCategory) {
		delete(BeanUtil.getBean(ESMapper.class).toES(subCategory));
	}

	private void delete(ESSubCategories es) {
		BeanUtil.getBean(IngestionService.class).delete(es);
	}
}
