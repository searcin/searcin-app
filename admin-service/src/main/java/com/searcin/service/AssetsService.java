package com.searcin.service;

import java.util.List;

import com.searcin.entity.Assets;

public interface AssetsService {
	Assets findById(Integer id);

	Assets save(Assets assets);

	List<String> untrackedS3Files();

	List<Assets> findAll();
}
