package com.searcin.listener;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;

import com.searcin.document.ESVendors;
import com.searcin.entity.Vendors;
import com.searcin.mapper.ESMapper;
import com.searcin.service.BeanUtil;
import com.searcin.service.IngestionService;

public class VendorsListener {
	
	@PreRemove
	private void removeServices(Vendors vendor) {
	   vendor.getServices().removeAll(vendor.getServices());
	}
	
	@PostPersist
	public void save(Vendors vendor) {
		save(BeanUtil.getBean(ESMapper.class).toES(vendor));
	}
	
	private void save(ESVendors vendor) {
		BeanUtil.getBean(IngestionService.class).save(vendor);
	}
	
	@PostUpdate
	public void update(Vendors vendor) {
		update(BeanUtil.getBean(ESMapper.class).toES(vendor));
	}
	
	private void update(ESVendors vendor) {
		BeanUtil.getBean(IngestionService.class).update(vendor);
	}
	
	@PostRemove
	public void delete(Vendors vendor) {
		delete(BeanUtil.getBean(ESMapper.class).toES(vendor));
	}

	private void delete(ESVendors esVendor) {
		BeanUtil.getBean(IngestionService.class).delete(esVendor);
	}
}
