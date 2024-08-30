package com.example.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.calculator.input.Input;
import com.example.calculator.service.Service;

@RestController
public class Controller {
	
	@Autowired
	Service service;
	
	@GetMapping("/add")
	public int add(@RequestBody Input input) {
		return service.add(input);
	}
}
