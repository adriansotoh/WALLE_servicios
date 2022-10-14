package com.walle.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PruebaController {

	@GetMapping("/")
	public String home() {
		return ("<h1>Welcome</>");
	}
	
}
