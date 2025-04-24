package com.sist.web;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.service.*;
import com.sist.vo.*;
@Controller
public class GoodsController {
	@Autowired
	private GoodsService service;
	/*
	 *  <a> locationc.href=""
	 *  axios.get => vue / react
	 *  			------------ fetch
	 *  $.ajax({
	 *  	type:get
	 *  })
	 *  => default
	 *  
	 *  <form> axios.post
	 *  $.ajax({
	 *  	type:post
	 *  })
	 */
	@GetMapping("goods/list.do")
	public String goods_list(Model model) {
		model.addAttribute("main_jsp","../goods/list.jsp");
		return "main/main";
	}
	@GetMapping("goods/detail.do")
	public String goods_detail(int no,Model model,HttpSession session) {
		String id=(String)session.getAttribute("userid");
		model.addAttribute("sessionId",id);
		model.addAttribute("no",no);
		model.addAttribute("main_jsp","../goods/detail.jsp");
		return "main/main";
	}
	@GetMapping("mypage/cart_list.do")
	public String mypage_cart_list(Model model,HttpSession session){
		String userid=(String)session.getAttribute("userid");
		List<CartVO> list=service.goodsCartListData(userid);
		model.addAttribute("list",list);
		model.addAttribute("mypage_jsp","../mypage/cart_list.jsp");
		model.addAttribute("main_jsp","../mypage/mypage_main.jsp");
		return "main/main";
	}
	@GetMapping("mypage/cart_delete.do")
	public String mypage_cart_delete(int cno) {
		service.goodsCartCancel(cno);
		return "redirect:../mypage/cart_list.do";
	}
	@GetMapping("mypage/buy_list.do")
	public String mypage_buy_list(Model model,HttpSession session){
		String userid=(String)session.getAttribute("userid");
		List<CartVO> list=service.goodsBuyListData(userid);
		model.addAttribute("list",list);
		model.addAttribute("mypage_jsp","../mypage/buy_list.jsp");
		model.addAttribute("main_jsp","../mypage/mypage_main.jsp");
		return "main/main";
	}
}
