package com.searcin.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.searcin.dto.CategoriesDto;
import com.searcin.dto.SuggestDto;
import com.searcin.dto.VendorsDto;
import com.searcin.service.SearchService;
import com.searcin.utils.DataConverter;
import com.searcin.view.Views;

@RestController
@RequestMapping("/search")
public class SearchController {
	
	private final Logger log = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	private SearchService searchService;

	@Autowired
	private DataConverter convert;
	
	@JsonView(Views.CategoryListId.class)
	@RequestMapping(value = "/categories")
	public List<CategoriesDto> categories() {
		return searchService.findCategories().stream().map(item -> convert.toDto(item))
				.collect(Collectors.toList());
	}

	@RequestMapping(value = "/suggest")
	public List<SuggestDto> suggest(@RequestParam String qs) {
		log.info("Searching for a string {}", qs);
		return convert.toDto(searchService.suggest(qs));
	}
	
	@RequestMapping(value = "/vendor/{id}")
	public VendorsDto vendor(@PathVariable Integer id) {
		return convert.toDto(searchService.findVendor(id));
	}
}
