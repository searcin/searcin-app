package com.searcin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.searcin.service.AssetsService;

@RestController
@PreAuthorize("hasAnyRole('ADMIN')")
@RequestMapping("/assets")
public class AssetsController {
	
	@Autowired
	private AssetsService assetsService;
	
	@RequestMapping("/untracked")
	public List<String> list() {
		return assetsService.untrackedS3Files();
	}
	
	
}
