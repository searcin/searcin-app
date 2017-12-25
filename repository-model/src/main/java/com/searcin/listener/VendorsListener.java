package com.searcin.listener;

import java.util.Optional;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import com.searcin.document.ESVendors;
import com.searcin.entity.Vendors;
import com.searcin.mapper.ESMapper;
import com.searcin.service.BeanUtil;
import com.searcin.service.IngestionService;

public class VendorsListener {

	@PrePersist
	@PreUpdate
	public void active(Vendors vendor) {
		vendor.setIsActive(Optional.ofNullable(vendor.getIsActive()).orElse(true));
	}

	@PostPersist
	@PostUpdate
	public void save(Vendors vendor) {
		if (vendor.getIsActive())
			save(BeanUtil.getBean(ESMapper.class).toES(vendor));
		else
			delete(BeanUtil.getBean(ESMapper.class).toES(vendor));
	}

	@Transactional
	private void save(ESVendors vendor) {
		BeanUtil.getBean(IngestionService.class).save(vendor);
	}

	@Transactional
	private void delete(ESVendors esVendor) {
		BeanUtil.getBean(IngestionService.class).delete(esVendor);
	}
}
