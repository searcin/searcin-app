package com.searcin.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.searcin.entity.Timings;

public interface TimingsRepository extends Repository<Timings, Long> {
	List<Timings> findAll();
	Timings save(Timings timing);
}
