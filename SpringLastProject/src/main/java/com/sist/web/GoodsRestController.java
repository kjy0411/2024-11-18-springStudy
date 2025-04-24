package com.sist.web;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.service.*;
import com.sist.vo.*;
@RestController
public class GoodsRestController {
	@Autowired
	private GoodsService service;
	
	@GetMapping("goods/list_vue.do")
	public Map goods_list(int page){
		int rowSize=12;
		int start=(page-1)*rowSize+1;
		int end=page*rowSize;
		List<GoodsVO> list=service.busanGoodsListData(start, end);
		int totalpage=service.busanGoodsTotalPage();
		
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
	@GetMapping("goods/detail_vue.do")
	public GoodsVO goods_detail(int no) {
		GoodsVO vo=service.busanGoodsDetailData(no);
		return vo;
	}
}
