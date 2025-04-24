package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import javax.servlet.http.HttpSession;

import com.sist.service.*;
import com.sist.vo.*;
@RestController
public class ReserveRestController {
	@Autowired
	private ReserveService service;
	
	@GetMapping("reserve/main_vue.do")
	public Map reserve_main() {
		Map map=new HashMap();
		List<FoodVO> list=service.busanFoodReserveData();
		List<String> tList=new ArrayList<String>();
		List<String> iList=new ArrayList<String>();
		// => 시간
		tList.add("17:00");
		tList.add("18:00");
		tList.add("19:00");
		tList.add("20:00");
		tList.add("21:00");
		tList.add("22:00");
		tList.add("23:00");
		tList.add("24:00");
		// => 인원
		for(int i=1;i<=10;i++) {
			iList.add(String.valueOf(i));
		}
		iList.add("단체");
		
		map.put("list", list);
		map.put("time", tList);
		map.put("inwon", iList);
		return map;
	}
	@PostMapping("reserve/reserve_vue_ok.do")
	public String reserve_vue_ok(ReserveVO vo,HttpSession session) {
		String result="";
		try {
			String userid=(String)session.getAttribute("userid");
			vo.setUserid(userid);
			service.reserveInsert(vo);
			result="yes";
		} catch (Exception e) {
			result=e.getMessage();
		}
		
		return result;
	}
}
