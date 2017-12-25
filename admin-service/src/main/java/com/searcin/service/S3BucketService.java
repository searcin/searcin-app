package com.searcin.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3ObjectSummary;

public interface S3BucketService {

	String getHost();

	List<S3ObjectSummary> list(String prefix);

	void upload(MultipartFile file, String path) throws IOException;

	void delete(String key);

}
