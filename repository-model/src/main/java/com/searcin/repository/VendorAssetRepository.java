package com.searcin.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.searcin.entity.VendorAsset;

@Transactional
public interface VendorAssetRepository extends Repository<VendorAsset, Integer> {
	
	void save(VendorAsset vendorAsset);
	
	@Query("select count(*) from VendorAsset va where va.vendor.id = ?1 and va.assetType = ?2")
	Integer countByVendor(Integer id, String assetType);

	@Query("select va from VendorAsset va where va.vendor.id = ?1 and va.assetType = ?2")
	List<VendorAsset> findByVendor(Integer id, String assetType);

	List<VendorAsset> findByVendor_id(Integer id);

	VendorAsset findById(Integer id);

	void delete(VendorAsset vendorAsset);

	void deleteById(Integer id);
}
