package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class TestContoller {

	@RequestMapping("")
	public String toStart() {
		return "common/afterLogin";
	}

	@RequestMapping("/finish")
	public String finish() {
		return "finish";
	}
}
