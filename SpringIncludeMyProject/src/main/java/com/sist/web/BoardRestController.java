package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.service.BoardService;
import com.sist.vo.BoardVO;

@RestController
@RequestMapping("board/")
public class BoardRestController {
	@Autowired
	private BoardService service;
	
	@PostMapping(value = "pwd_check.do",produces = "text/plain;charset=UTF-8")
	public String board_pwd_check(int no, String pwd) {
		String result="no";
		System.out.println(no);
		System.out.println(pwd);
		BoardVO vo=new BoardVO();
		vo.setNo(no);
		vo.setPwd(pwd);
		if(service.boardCheckPassWord(vo)) {
			result="yes";
		}
		return result;
	}
}
