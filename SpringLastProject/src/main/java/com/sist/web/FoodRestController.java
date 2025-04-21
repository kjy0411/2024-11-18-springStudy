package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.service.*;
import com.sist.vo.*;
// BI
@RestController
@RequestMapping("food")
public class FoodRestController {
	@Autowired
	private FoodService service;
	
	@GetMapping("list_vue.do")
	public Map food_list(int page){
		int rowSize=12;
		int start=(page-1)*rowSize+1;
		int end=page*rowSize;
		List<FoodVO> list=service.busanFoodListData(start, end);
		int totalpage=service.busanFoodTotalPage();
		
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) endPage=totalpage;
		
		Map map=new HashMap();
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		
		return map;
	}
}
