package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import com.sist.commons.*;
import com.sist.service.*;
import com.sist.vo.*;

@Controller
@RequestMapping("board/")
public class BoardController {
	@Autowired
	private BoardService service;
	
	@GetMapping("list.do")
	public String board_list(String page,Model model) {
		int rowSize=10;
		final int BLOCK=10;
		int count=service.boardRowCount();
		Map map=CommonsPagination.pageConfig(page, count, rowSize, BLOCK);
		List<BoardVO> bList=service.boardListData(map);
		
		model.addAttribute("bList",bList);
		model.addAttribute("main_jsp","../board/list.jsp");
		return "main/main";
	}
	@GetMapping("insert.do")
	public String board_insert(Model model) {
		model.addAttribute("main_jsp","../board/insert.jsp");
		return "main/main";
	}
	@PostMapping("insert_ok.do")
	public String board_insert_ok(BoardVO vo,Model model) {
		service.boardInsert(vo);
		return "redirect:../board/list.do";
	}
	@GetMapping("detail.do")
	public String board_detail(int no,Model model) {
		BoardVO vo=service.boardDetailData(no, 1);
		model.addAttribute("vo",vo);
		model.addAttribute("main_jsp","../board/detail.jsp");
		return "main/main";
	}
	@GetMapping("update.do")
	public String board_update(int no,Model model) {
		BoardVO vo=service.boardDetailData(no, 0);
		model.addAttribute("vo",vo);
		model.addAttribute("main_jsp","../board/update.jsp");
		return "main/main";
	}
	@PostMapping("update_ok.do")
	public String board_update_ok(BoardVO vo,Model model) {
		service.boardUpdate(vo);
		model.addAttribute("main_jsp","../board/update.jsp");
		return "main/main";
	}
}
