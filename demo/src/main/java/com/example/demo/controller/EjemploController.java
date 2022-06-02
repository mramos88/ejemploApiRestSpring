package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EjemploController {
	@GetMapping("/message")
	public String getMessage() {
		String msg = "Llegue al servidor";
		
		return msg;
		
	}
}
