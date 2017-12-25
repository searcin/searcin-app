package com.searcin.listener;

import java.util.Optional;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import com.searcin.document.ESCategories;
import com.searcin.entity.Categories;
import com.searcin.mapper.ESMapper;
import com.searcin.service.BeanUtil;
import com.searcin.service.IngestionService;

public class CategoriesListener {

	@PrePersist
	@PreUpdate
	public void active(Categories category) {
		category.setIsActive(Optional.ofNullable(category.getIsActive()).orElse(true));
	}

	@PostPersist
	@PostUpdate
	public void save(Categories category) {
		if (category.getIsActive())
			save(BeanUtil.getBean(ESMapper.class).toES(category));
		else
			delete(BeanUtil.getBean(ESMapper.class).toES(category));
	}

	@Transactional
	private void save(ESCategories esCategory) {
		BeanUtil.getBean(IngestionService.class).save(esCategory);
	}

	@Transactional
	private void delete(ESCategories esCategory) {
		BeanUtil.getBean(IngestionService.class).delete(esCategory);
	}
}
