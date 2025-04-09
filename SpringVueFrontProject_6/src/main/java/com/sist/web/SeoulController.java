package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SeoulController {
	@GetMapping("main/list.do")
	public String main_list() {
		return "main/list";
	}
}
