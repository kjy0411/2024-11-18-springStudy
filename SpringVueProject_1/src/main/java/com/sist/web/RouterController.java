package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouterController {
	@GetMapping("main/main.do")
	public String main_main(Model model) {
		model.addAttribute("main_jsp","../recipe/list.jsp");
		return "main/main";
	}
	@GetMapping("recipe/detail.do")
	public String recipe_detail(Model model) {
		model.addAttribute("main_jsp","../recipe/detail.jsp");
		return "main/main";
	}
	@GetMapping("food/list.do")
	public String food_list(Model model) {
		model.addAttribute("main_jsp","../food/list.jsp");
		return "main/main";
	}
	@GetMapping("food/detail.do")
	public String food_detail(Model model) {
		model.addAttribute("main_jsp","../food/detail.jsp");
		return "main/main";
	}
	@GetMapping("seoul/location.do")
	public String seoul_location(Model model) {
		model.addAttribute("main_jsp","../seoul/location.jsp");
		return "main/main";
	}
	@GetMapping("seoul/shop.do")
	public String seoul_shop(Model model) {
		model.addAttribute("main_jsp","../seoul/shop.jsp");
		return "main/main";
	}
	@GetMapping("seoul/location_detail.do")
	public String seoul_location_detail(Model model) {
		model.addAttribute("main_jsp","../seoul/location_detail.jsp");
		return "main/main";
	}
	@GetMapping("seoul/shop_detail.do")
	public String seoul_shop_detail(Model model) {
		model.addAttribute("main_jsp","../seoul/shop_detail.jsp");
		return "main/main";
	}
}
