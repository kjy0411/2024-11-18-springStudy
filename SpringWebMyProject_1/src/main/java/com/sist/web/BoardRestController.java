package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.service.BoardService;
import com.sist.vo.BoardVO;

@RestController
@RequestMapping("board/")
public class BoardRestController {
	@Autowired
	public BoardService service;
	
	@PostMapping(value = "update_ok.do",produces = "text/html;charset=UTF-8")
	public String board_update_ok(BoardVO vo) {
		String result="";
		boolean check=service.boardUpdate(vo);
		if(check) {
			result="<script>"
					+ "location.href='detail.do?no="+vo.getNo()+"&type=1'"
					+ "</script>";
		}else {
			result="<script>"
				+ "alert('비밀번호가 틀립니다');"
				+ "history.back();"
				+ "</script>";
		}
		return result;
	}
	@PostMapping(value = "delete_ok.do",produces = "text/html;charset=UTF-8")
	public String board_delete_ok(int no,String pwd) {
		String result="";
		boolean check=service.boardDelete(no, pwd);
		if(check) {
			result="<script>"
					+ "location.href='list.do';"
					+ "</script>";
		}else {
			result="<script>"
					+ "alert('비밀번호가 틀립니다');"
					+ "history.back();"
					+ "</script>";
		}
		return result;
	}
}
