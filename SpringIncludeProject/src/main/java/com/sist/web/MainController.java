package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;
import com.sist.commons.*;
import com.sist.service.*;
import com.sist.vo.*;
@Controller
public class MainController {
	@Autowired
	private RecipeService service;
	
	@GetMapping("main/main.do")
	public String main_main(String page,Model model) {
		int totalpage=service.recipeTotalPage();
		Map map=CommonsPagination.pageConfig(page,totalpage, 12,10);
		List<RecipeVO> list=service.recipeListData(map);
		int curpage=(int)map.get("curpage");
		int startPage=(int)map.get("startPage");
		int endPage=(int)map.get("endPage");
		
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		
		model.addAttribute("main_jsp","../main/home.jsp");
		return "main/main";
	}
}
