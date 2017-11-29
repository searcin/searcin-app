package com.searcin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

	@RequestMapping("/ok")
	public String ok() {
		return "ok";
	}
}
