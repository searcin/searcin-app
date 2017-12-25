package com.searcin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.searcin.constant.EntityType;
import com.searcin.service.TrashService;

@Service
public class TrashServiceImpl implements TrashService {
	
	@Autowired
	private EntityManager entityManager;
	
	private static final String trashItemsQuery = "select id, name, updated_on, updated_by, type  "
			+ "from(select c.id,c.name,c.updated_on,c.updated_by,:cType as type from `categories` as c where c.is_active = false\r\n" + 
			"union\r\n" + 
			"select s.id,s.name,s.updated_on,s.updated_by,:scType as type from `sub_categories` as s where s.is_active = false\r\n" + 
			"union\r\n" + 
			"select a.id,a.name,a.updated_on,a.updated_by,:aType as type from `areas` as a where a.is_active = false\r\n" + 
			"union\r\n" + 
			"select s.id,s.name,s.updated_on,s.updated_by,:sType as type from `services` as s where s.is_active = false\r\n" + 
			"union\r\n" + 
			"select v.id,v.name,v.updated_on,v.updated_by,:vType as type from `vendors` as v where v.is_active = false) trash "
			+ "order by updated_on desc limit :limit offset :index";
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getItems(Pageable pageable) {
		Query query = entityManager.createNativeQuery(trashItemsQuery);
		query.setParameter("cType", EntityType.CATEGORIES.toString());
		query.setParameter("scType", EntityType.SUBCATEGORIES.toString());
		query.setParameter("sType", EntityType.SERVICES.toString());
		query.setParameter("aType", EntityType.AREAS.toString());
		query.setParameter("vType", EntityType.VENDORS.toString());
		query.setParameter("limit", pageable.getPageSize());
		query.setParameter("index", pageable.getPageNumber());
		List<Object[]> result = query.getResultList();
		return result.stream().map(item -> {
			Map<String, Object> obj = new HashMap<>();
			obj.put("id", item[0]);
			obj.put("name", item[1]);
			obj.put("updatedOn", item[2]);
			obj.put("updatedBy", item[3]);
			obj.put("type", item[4]);
			return obj;
		}).collect(Collectors.toList());
	}
}
