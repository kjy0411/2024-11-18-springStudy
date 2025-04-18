package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import com.sist.dao.*;
import com.sist.mapper.BoardMapper;
import com.sist.vo.*;
@Controller
public class BoardController {
	
	@GetMapping("/board/list")
	public String board_list() {
		return "board/list";
	}
	@GetMapping("/board/insert")
	public String board_insert() {
		return "board/insert";
	}
	@GetMapping("/board/detail")
	public String board_detail() {
		return "board/detail";
	}
	@GetMapping("/board/update")
	public String board_update() {
		return "board/update";
	}
	@GetMapping("/board/delete")
	public String board_delete() {
		return "board/delete";
	}
}
