package com.sist.web;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.service.*;
import com.sist.vo.*;
@RestController
public class BusanInfoRestController {
	@Autowired
	public BusanInfoService service;
	
	@GetMapping("busan/info_vue.do")
	public Map busan_info_vue(int page, int cno) {
		// 데이터베이스에 설정할 변수
		Map map=new HashMap();
		map.put("cno", cno);
		map.put("start", page*12-11);
		map.put("end", page*12);
		
		List<BusanInfoVO> list=service.BusanInfoListData(map);
		int totalpage=service.BusanInfoTotalPage(cno);
		
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) endPage=totalpage;
		// Vue로 전송
		map=new HashMap();
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage",endPage);
		
		return map;
	}
	@GetMapping("busan/info_detail_vue.do")
	public Map busan_info_detail_vue(int no) {
		Map map=new HashMap();
		BusanInfoVO vo=service.BusanInfoDetailData(no);
		map.put("vo", vo);
		return map;
	}
}
