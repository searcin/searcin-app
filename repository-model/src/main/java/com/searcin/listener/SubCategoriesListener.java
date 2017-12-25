package com.searcin.listener;

import java.util.Optional;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import com.searcin.document.ESSubCategories;
import com.searcin.entity.SubCategories;
import com.searcin.mapper.ESMapper;
import com.searcin.service.BeanUtil;
import com.searcin.service.IngestionService;

public class SubCategoriesListener {

	@PrePersist
	@PreUpdate
	public void active(SubCategories subCategory) {
		subCategory.setIsActive(Optional.ofNullable(subCategory.getIsActive()).orElse(true));
	}

	@PostPersist
	@PostUpdate
	public void save(SubCategories subCategory) {
		if (subCategory.getIsActive())
			save(BeanUtil.getBean(ESMapper.class).toES(subCategory));
		else
			delete(BeanUtil.getBean(ESMapper.class).toES(subCategory));
	}

	@Transactional
	private void save(ESSubCategories esSubCategory) {
		BeanUtil.getBean(IngestionService.class).save(esSubCategory);
	}

	@Transactional
	private void delete(ESSubCategories esSubCategory) {
		BeanUtil.getBean(IngestionService.class).delete(esSubCategory);
	}
}
