package com.searcin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.searcin.dto.SuggestDto;
import com.searcin.dto.VendorsDto;
import com.searcin.service.SearchService;
import com.searcin.utils.DataConverter;

@RestController
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private SearchService searchService;

	@Autowired
	private DataConverter convert;

	@RequestMapping(value = "/suggest")
	public List<SuggestDto> suggest(@RequestParam String qs) {
		return convert.toDto(searchService.suggest(qs));
	}
	
	@RequestMapping(value = "/vendor/{id}")
	public VendorsDto vendor(@PathVariable Integer id) {
		return convert.toDto(searchService.findVendor(id));
	}
}
