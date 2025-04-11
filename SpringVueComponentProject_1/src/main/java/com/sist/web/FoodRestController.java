package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.FoodVO;
@RestController
public class FoodRestController {
	@Autowired
	private FoodDAO dao;
	/*
	@PostMapping("food/vue_check2_vue.do")
	public Map vue_check2(String[] f,String ss,int no) {
		System.out.println(ss);
		System.out.println(no);
		System.out.println(f[0]);
		System.out.println(f[1]);
		Map map=new HashMap();
		map.put("f", f);
		return map;
	}
	*/
	@PostMapping("food/list_vue.do")
	public List<FoodVO> food_list(int page,String ss,String[] fd){
		System.out.println(page);
		System.out.println(fd.toString());
		System.out.println(ss);
		int rowSize=12;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		
		Map map=new HashMap();
		map.put("ss", ss);
		map.put("fdArr", fd);
		map.put("start", start);
		map.put("end", end);
		List<FoodVO> list=dao.foodFindData(map);
		
		return list;
	}
}
