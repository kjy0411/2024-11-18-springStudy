package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;

import java.util.*;
@Controller
public class FoodController {
	@Autowired
	private FoodDAO dao;
	@GetMapping("food/list.do")
	public String food_list() {
		return "food/list";
	}
	@RequestMapping("food/food_list.do")
	public String food_list2(String page,String ss,String[] fd,Model model) {
		if(page==null) {
			page="1";
		}
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		
		Map map=new HashMap();
		map.put("ss",ss);
		map.put("fdArr", fd);
		map.put("start", start);
		map.put("end", end);
		
		List<FoodVO> list=dao.foodFindData(map);
		int totalpage=dao.foodTotalPage(map);
		
		model.addAttribute("list",list);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("curpage",curpage);
		return "food/food_list";
	}
}
