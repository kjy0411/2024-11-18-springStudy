package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FoodController {
	@GetMapping("food/list.do")
	public String recipe_list() {
		return "food/list";
	}
	@GetMapping("food/find.do")
	public String recipe_find() {
		return "food/find";
	}
	@GetMapping("food/detail.do")
	public String recipe_detail() {
		return "food/detail";
	}
}
