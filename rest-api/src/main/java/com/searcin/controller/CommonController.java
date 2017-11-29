package com.searcin.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.searcin.dto.CategoriesDto;

@RestController
@RequestMapping("/common")
public class CommonController {
	
	@RequestMapping("/info")
	public List<CategoriesDto> info() {
		return null;
	}
}
 