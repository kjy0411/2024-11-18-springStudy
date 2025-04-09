package com.sist.web;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
@RestController
public class FoodRestController {
	@Autowired
	private FoodDAO dao;
	
	@GetMapping(value = "food/list_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_list(int page) throws Exception{
		int rowSize=12;
		int start=(rowSize*page)-(rowSize-1);
		int end=(rowSize*page);
		List<FoodVO> list=dao.foodListData(start, end);
		int totalpage=dao.foodTotalPage();
		
		Map map=new HashedMap();
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		return json;
	}
	@GetMapping(value = "food/find_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_find(int page,String fd) throws Exception{
		int rowSize=12;
		int start=(rowSize*page)-(rowSize-1);
		int end=(rowSize*page);

		Map map=new HashedMap();
		map.put("start", start);
		map.put("end", end);
		map.put("fd", fd);
		List<FoodVO> list=dao.foodFindListData(map);
		int totalpage=dao.foodFindTotalPage(fd);
		
		map=new HashMap();
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		return json;
	}
	@GetMapping(value = "food/detail_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_detail(int fno) throws Exception{
		
		FoodVO vo=dao.foodDetailData(fno);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
	
}
