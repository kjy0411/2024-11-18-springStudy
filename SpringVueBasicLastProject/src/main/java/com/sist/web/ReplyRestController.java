package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import javax.servlet.http.HttpSession;

import com.sist.dao.*;
import com.sist.vo.*;
@RestController
public class ReplyRestController {
	@Autowired
	private ReplyDAO dao;
	
	public List<ReplyVO> replyListData(int bno) {
		List<ReplyVO> list=dao.replyListData(bno);
		return list;
	}
	
	@GetMapping("reply/list_vue.do")
	public List<ReplyVO> replyData(int bno){
		return replyListData(bno);
	}
	@PostMapping("reply/insert_vue.do")
	public List<ReplyVO> replyInsert(ReplyVO vo,HttpSession session){
		System.out.println(vo.getBno()+":"+vo.getMsg());
		String id=(String)session.getAttribute("id");
		String name=(String)session.getAttribute("name");
		vo.setId(id);
		vo.setName(name);
		
		dao.replyInsert(vo);
		
		return replyListData(vo.getBno());
	}
	@GetMapping("reply/update_vue.do")
	public List<ReplyVO> replyUpdate(ReplyVO vo){
		dao.replyUpdate(vo);
		return replyListData(vo.getBno());
	}
	@GetMapping("reply/delete_vue.do")
	public List<ReplyVO> replyDelete(int no, int bno){
		dao.replyDelete(no);
		return replyListData(bno);
	}
}
