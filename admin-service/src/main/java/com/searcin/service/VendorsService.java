package com.searcin.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.searcin.constant.AssetType;
import com.searcin.entity.Addresses;
import com.searcin.entity.Assets;
import com.searcin.entity.Contacts;
import com.searcin.entity.Timings;
import com.searcin.entity.Vendors;

public interface VendorsService {

	List<Vendors> findNames();
	
	List<Vendors> findAll();

	Vendors save(Vendors vendors);

	void saveServices(Integer id, List<Integer> services);

	void delete(Integer id);

	void deleteAll();

	Vendors findById(Integer id);

	List<Vendors> findByCategoryId(Integer category_id);

	List<Vendors> findBySubCategoryId(Integer sub_category_id);

	Vendors detail(Integer id);

	Page<Vendors> findNames(Pageable pageable);

	void restore(Integer id);

	void trash(Integer id);

	Addresses findAddress(Integer id);

	Addresses saveAddress(Addresses address, Integer id);

	Contacts saveContact(Contacts entity, Integer id);

	Contacts findContact(Integer id);

	Boolean isExists(Integer id);
	
	Assets uploadAsset(Integer id, MultipartFile file, AssetType type);

	List<Assets> getAssets(Integer id, AssetType vendorlogo);

	Assets deleteAsset(Integer id, Integer assetId);

	List<Timings> saveTiming(Integer id, List<Timings> timings);

	List<Timings> getTiming(Integer id);

}
