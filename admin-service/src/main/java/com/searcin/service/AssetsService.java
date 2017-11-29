package com.searcin.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3ObjectSummary;

public interface AssetsService {
	boolean isBucketAvailable();
	boolean isValidAssetType(String type);
	boolean isWithinLimit(Integer size, Integer id,String type);
	String getHost();
	String getUploadedBy(S3ObjectSummary obj);
	List<S3ObjectSummary> list(Integer id, String type);
	void upload(MultipartFile[] multipartFiles, Integer id, String type, String username);
	void delete(String key);
	void deleteAll(Integer id);
}
