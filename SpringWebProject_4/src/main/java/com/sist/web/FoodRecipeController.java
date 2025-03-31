package com.sist.web;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.service.FoodRecipeService;
import com.sist.vo.FoodVO;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
// JSP로 요청 처리 결과값 전송
@Controller
@RequestMapping("food/")
public class FoodRecipeController {
	@Autowired
	private FoodRecipeService service;
	
	//목록 => 405
	/*  
	 *  404 파일 없음
	 *  405 get/post
	 *  500 오류(db,데이터형...)
	 *  
	 */
	@GetMapping("food_list.do") // Target : Method
	public String food_list(String page,Model model) {
		// 처음 => null값 포함 => 매개변수 => String으로 받는다
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int totalpage=service.foodTotalPage();
		
		int rowSize=12;
		Map map=new HashedMap();
		map.put("start", (curpage*rowSize)-(rowSize-1));
		map.put("end", (curpage*rowSize));
		
		List<FoodVO> list=service.foodListData(map);
		
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
		return "food/food_list";
	}
	//쿠키
	@GetMapping("food_detail_before.do")
	public String food_detail_before(String fno,HttpServletResponse response,RedirectAttributes ra) {
		Cookie cookie=new Cookie("spring_"+fno, fno);
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
		
		ra.addAttribute("fno",fno); //sendRedirect인경우 사용 / ?대신에 사용
		return "redirect:food_detail.do";
	}
	//상세보기
	@GetMapping("food_detail.do")
	public String food_detail(int fno,Model model) {
		FoodVO vo=service.foodDetailData(fno);
		
		model.addAttribute("vo",vo);
		return "food/food_detail";
	}
}
