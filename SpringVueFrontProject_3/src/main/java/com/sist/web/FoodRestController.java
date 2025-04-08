package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.*;
import com.sist.vo.*;
@RestController
public class FoodRestController {
   // 필요한 객체 => 스프링에 요청 
   @Autowired
   private FoodDAO dao;
   
   @GetMapping(value="food/list_vue.do",produces = "text/plain;charset=UTF-8")
   public String food_list_vue() throws Exception {
	   List<FoodVO> list=dao.foodListData();
	   for(FoodVO vo:list) {
		   vo.setPoster("https://www.menupan.com"+vo.getPoster());
	   }
	   // javascript에서 인식 (연동) => JSON
	   ObjectMapper mapper=
			   new ObjectMapper();
	   String json=mapper.writeValueAsString(list);
	   return json;
   }
}
