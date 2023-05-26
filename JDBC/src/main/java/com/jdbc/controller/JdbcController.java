package com.jdbc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JdbcController {
	@GetMapping("/get")
	public String get() {
		return "get";
	}
}
