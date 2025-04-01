package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.sist.service.*;
import com.sist.vo.*;

@Controller
@RequestMapping("board/")
public class BoardController {
	@Autowired
	private BoardService service;
	
	@GetMapping("list.do")
	public String board_list(String page,Model model) {
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int count=service.boardRowCount();
		int totalpage=(int)(Math.ceil(count/10.0));
		
		List<BoardVO> list=service.boardListData((curpage*10)-9, curpage*10);
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("main_jsp","../board/list.jsp");
		return "main/main";
	}
	@GetMapping("insert.do")
	public String board_insert() {
		
		return "board/insert";
	}
	@PostMapping("insert_ok.do")
	public String board_insert_ok(BoardVO vo) {
		service.boardInsert(vo);
		return "redirect:list.do";
	}
	@GetMapping("detail_before.do")
	public String board_detail_before(String no,HttpServletResponse response) {
		Cookie cookie=new Cookie("board_"+no, no);
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		
		response.addCookie(cookie);
		
		return "redirect:detail.do?no="+no;
	}
	@GetMapping("detail.do")
	public String board_detail(int no,String type,Model model) {
		if(type==null) type="0";
		BoardVO vo=service.boardDetailData(no,Integer.parseInt(type));
		
		model.addAttribute("vo",vo);
		return "board/detail";
	}
	@GetMapping("update.do")
	public String board_update(int no,Model model) {
		BoardVO vo=service.boardDetailData(no,1);
		
		model.addAttribute("vo",vo);
		return "board/update";
	}
	@GetMapping("delete.do")
	public String board_delete(int no,Model model) {
		model.addAttribute("no",no);
		return "board/delete";
	}
}
