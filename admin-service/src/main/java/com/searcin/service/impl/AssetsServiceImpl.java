package com.searcin.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.searcin.entity.Assets;
import com.searcin.exception.ObjectNotFoundException;
import com.searcin.repository.AssetsRepository;
import com.searcin.service.AssetsService;
import com.searcin.service.S3BucketService;

@Service
public class AssetsServiceImpl implements AssetsService {

	@Autowired
	private AssetsRepository assetsRepository;

	@Autowired
	private S3BucketService s3BucketService;

	@Value("${assets.environment}")
	private String environment;

	@Override
	public List<Assets> findAll() {
		return assetsRepository.findAll();
	}

	@Override
	public Assets findById(Integer id) {
		return Optional.ofNullable(assetsRepository.findById(id))
				.orElseThrow(() -> new ObjectNotFoundException("Asset not found!"));
	}

	@Override
	public Assets save(Assets asset) {
		return assetsRepository.save(asset);
	}

	@Override
	public List<String> untrackedS3Files() {
		List<String> assetKeys = findAll().stream().map(item -> item.getKey()).collect(Collectors.toList());
		return s3BucketService.list(environment).stream().filter(item -> assetKeys.indexOf(item.getKey()) == -1)
				.map(item -> item.getKey()).collect(Collectors.toList());
	}
}
