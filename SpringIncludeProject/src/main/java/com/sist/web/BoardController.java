package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
@Controller
@RequestMapping("board/")
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	@GetMapping("list.do")
	public String board_list(String page,Model model) {
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		List<BoardVO> bList=dao.boardListData(Integer.parseInt(page));
		int count=dao.boardRowCount();
		int totalpage=(int)(Math.ceil(count/10.0));
		count=count-((curpage*10)-10);
		
		model.addAttribute("bList",bList);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("count",count);
		model.addAttribute("main_jsp","../replyboard/list.jsp");
		return "main/main";
	}
	@GetMapping("insert.do")
	public String board_insert(Model model) {
		
		model.addAttribute("main_jsp","../replyboard/insert.jsp");
		return "main/main";
	}	
	@PostMapping("insert_ok.do")
	public String board_insert_ok(BoardVO vo) {
		dao.boardInsert(vo);
		return "redirect:../board/list.do";
	}
	@GetMapping("detail.do")
	public String board_detail(int no,Model model) {
		BoardVO vo=dao.boardDetailData(no);
		model.addAttribute("vo",vo);
		model.addAttribute("main_jsp","../replyboard/detail.jsp");
		return "main/main";
	}
	@GetMapping("update.do")
	public String board_update(int no,Model model) {
		BoardVO vo=dao.boardUpdateData(no);
		model.addAttribute("vo",vo);
		model.addAttribute("main_jsp","../replyboard/update.jsp");
		return "main/main";
	}
	@PostMapping("update_ok.do")
	public String board_update_ok(BoardVO vo,Model model) {
		
		return "redirect:../board/detail.do?no="+vo.getNo();
	}
	@GetMapping("reply.do")
	public String board_reply(int no,Model model) {
		model.addAttribute("main_jsp","../replyboard/reply.jsp");
		return "main/main";
	}
}
