package com.searcin.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

public interface TrashService {

	List<Map<String, Object>> getItems(Pageable pageable);

}
