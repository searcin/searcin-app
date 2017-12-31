package com.searcin.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.searcin.constant.AssetType;
import com.searcin.entity.Addresses;
import com.searcin.entity.Assets;
import com.searcin.entity.Contacts;
import com.searcin.entity.Services;
import com.searcin.entity.Vendors;
import com.searcin.exception.AwsS3Exception;
import com.searcin.exception.ObjectNotFoundException;
import com.searcin.exception.OutOfLimitException;
import com.searcin.repository.AssetsRepository;
import com.searcin.repository.ServicesRepository;
import com.searcin.repository.VendorsRepository;
import com.searcin.service.S3BucketService;
import com.searcin.service.VendorsService;

@Service
public class VendorsServiceImpl implements VendorsService {

	private final Logger log = LoggerFactory.getLogger(VendorsServiceImpl.class);

	@Value("${assets.vendor}")
	private String vendor;

	@Value("${assets.environment}")
	private String environment;

	@Autowired
	private VendorsRepository vendorsRepository;

	@Autowired
	private ServicesRepository servicesRepository;

	@Autowired
	private S3BucketService s3BucketService;

	@Autowired
	private AssetsRepository assetsRepository;

	@Override
	public Boolean isExists(Integer id) {
		return vendorsRepository.isExists(id);
	}

	@Override
	public List<Vendors> findAll() {
		return vendorsRepository.findAll();
	}

	@Override
	public Vendors findById(Integer id) {
		return Optional.ofNullable(vendorsRepository.findById(id))
				.orElseThrow(() -> new ObjectNotFoundException("Vendor not found!"));
	}

	@Override
	public Addresses findAddress(Integer id) {
		return Optional.ofNullable(findById(id).getAddress()).orElse(new Addresses());
	}

	@Override
	public Contacts findContact(Integer id) {
		return Optional.ofNullable(findById(id).getContact()).orElse(new Contacts());
	}

	@Override
	public Addresses saveAddress(Addresses address, Integer id) {
		Vendors vendor = findById(id);
		vendor.setAddress(address);
		Addresses savedAddress = save(vendor).getAddress();
		log.info("Address with vendor id {} saved to database {}", id, savedAddress);
		return savedAddress;
	}

	@Override
	public Contacts saveContact(Contacts contact, Integer id) {
		Vendors vendor = findById(id);
		vendor.setContact(contact);
		Contacts savedContact = save(vendor).getContact();
		log.info("Contact with vendor id {} saved to database {}", id, savedContact);
		return savedContact;
	}

	@Override
	public List<Vendors> findNames() {
		return vendorsRepository.findNames();
	}

	@Override
	public Vendors save(Vendors vendors) {
		return vendorsRepository.save(vendors);
	}

	@Override
	public void delete(Integer id) {
		// delete assets
		// vendorAssetService.deleteAssetsByVendor(id);
		// delete row
		vendorsRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		vendorsRepository.deleteAll();
	}

	@Override
	public List<Vendors> findByCategoryId(Integer category_id) {
		return vendorsRepository.findByCategory_id(category_id);
	}

	@Override
	public List<Vendors> findBySubCategoryId(Integer sub_category_id) {
		return vendorsRepository.findBySubCategory_id(sub_category_id);
	}

	@Override
	public void saveServices(Integer vendorId, List<Integer> services) {
		Vendors vendor = findById(vendorId);
		List<Services> listOfServices = new ArrayList<>();
		for (Integer id : services) {
			listOfServices.add(servicesRepository.findById(id));
		}
		vendor.setServices(listOfServices);
		save(vendor);
	}

	@Override
	public Vendors detail(Integer id) {
		return vendorsRepository.findById(id);
	}

	@Override
	public Page<Vendors> findNames(Pageable pageable) {
		return vendorsRepository.findNames(pageable);
	}

	@Override
	public void restore(Integer id) {
		Vendors vendor = findById(id);
		vendor.setIsActive(true);
		save(vendor);
	}

	@Override
	public void trash(Integer id) {
		Vendors vendor = findById(id);
		vendor.setIsActive(false);
		save(vendor);
	}

	@Override
	public List<Assets> uploadAsset(Integer id, MultipartFile file, AssetType type) {
		if (vendorsRepository.isExists(id)) {
			String path = environment + "/" + vendor + "/" + id + "/" + type.getValue() + "/"
					+ file.getOriginalFilename();
			if (vendorsRepository.countOfAssets(id, type.getValue()) < type.getMaxCount()) {
				try {
					log.info("Uploading to s3 bucket..........");
					s3BucketService.upload(file, path);

					log.info("Uploading the key to database...");
					Vendors vendor = findById(id);
					List<Assets> assets = vendor.getAssets();
					assets.add(new Assets(type.getValue(), path, s3BucketService.getHost() + path));
					vendor.setAssets(assets);
					Vendors savedVendor = save(vendor);
					return savedVendor.getAssets().stream().filter(item -> item.getType().equals(type.getValue()))
							.collect(Collectors.toList());
				} catch (IOException e) {
					throw new AwsS3Exception("Input stream conversion issue {}", e);
				}
			} else {
				throw new OutOfLimitException("Limit exceeded");
			}
		} else {
			throw new ObjectNotFoundException("Vendor not found!");
		}
	}

	@Override
	public List<Assets> getAssets(Integer id, AssetType type) {
		return findById(id).getAssets().stream().filter(item -> item.getType().equals(type.getValue()))
				.collect(Collectors.toList());
	}

	@Override
	public Assets deleteAsset(Integer id, Integer assetId) {
		// get asset from database
		log.info("Getting assets from database........");
		Assets asset = Optional.ofNullable(assetsRepository.findById(assetId))
				.orElseThrow(() -> new ObjectNotFoundException("Asset Not found!"));
		
		log.info("Deleting from aws s3 bucket........");
		s3BucketService.delete(asset.getKey());
		
		log.info("Removing from database........");
		Vendors vendor = findById(id);
		List<Assets> assets = vendor.getAssets();
		assets.remove(asset);
		vendor.setAssets(assets);
		save(vendor);
		
		return asset;
	}

}
