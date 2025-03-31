package com.sist.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.service.GoodsService;
import com.sist.vo.GoodsVO;

@Controller
@RequestMapping("goods/")
public class GoodsController {
	@Autowired
	private GoodsService service;
	
	@GetMapping("goods_list")
	public String goods_list(String page,Model model) {
		if(page==null)
			page="1";
		
		int curpage=Integer.parseInt(page);
		int totalpage=service.goodsTotalPage();
		
		int rowSize=12;
		Map map=new HashedMap();
		map.put("start", (curpage*rowSize)-(rowSize-1));
		map.put("end", (curpage*rowSize));
		
		List<GoodsVO> list=service.goodsListData(map);
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		//food_list로 전송
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		
		return "goods/goods_list";
	}
	//쿠키
		@GetMapping("goods_detail_before.do")
		public String goods_detail_before(String no,HttpServletResponse response,RedirectAttributes ra) {
			Cookie cookie=new Cookie("goods_"+no, no);
			cookie.setPath("/");
			cookie.setMaxAge(60*60*24);
			response.addCookie(cookie);
			
			ra.addAttribute("no",no); //sendRedirect인경우 사용 / ?대신에 사용
			return "redirect:goods_detail.do";
		}
		//상세보기
		@GetMapping("goods_detail.do")
		public String goods_detail(int no,Model model) {
			GoodsVO vo=service.goodsDetailData(no);
			
			model.addAttribute("vo",vo);
			return "goods/goods_detail";
		}
}
