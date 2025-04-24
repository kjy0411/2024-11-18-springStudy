package com.sist.web;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.service.*;
import com.sist.vo.*;

import oracle.jdbc.proxy.annotation.Post;
@RestController
public class CommentRestController {
	@Autowired
	private CommentService service;
	
	public Map commnosListData(int page,int cno,int type) {
		Map map=new HashMap();
		// 데이터베이스 연동
		int rowSize=10;
		map.put("start", (page*rowSize)-(rowSize-1));
		map.put("end", (page*rowSize));
		map.put("cno", cno);
		map.put("type", type);
		List<CommentVO> list=service.commentListData(map);
		int totalpage=service.commentTotalPage(cno, type);
		
		final int BLOCK=5;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) endPage=totalpage;
		
		// 값 읽기 => JSON으로 전송 MAP
		map=new HashMap();
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		return map;
	}
	// 목록
	@GetMapping("comment/list_vue.do")
	public Map comment_list_vue(int page,int cno, int type) {
		return commnosListData(page, cno, type);
	}
	// 추가
	@PostMapping("comment/insert_vue.do")
	public Map comment_insert_vue(CommentVO vo,HttpSession session) {
		String userid=(String)session.getAttribute("userid");
		String username=(String)session.getAttribute("username");
		String sex=(String)session.getAttribute("sex");
		vo.setUserid(userid);
		vo.setUsername(username);
		vo.setSex(sex);
		service.commentInsert(vo);
		return commnosListData(1, vo.getCno(), vo.getType());
	}
	// 수정
	@PostMapping("comment/update_vue.do")
	public Map comment_update_vue(CommentVO vo) {
		service.commentUpdate(vo.getMsg(), vo.getNo());
		return commnosListData(1, vo.getCno(), vo.getType());
	}
	// 대댓글
	@PostMapping("comment/reply_insert_vue.do")
	public Map comment_reply_insert_vue(CommentVO vo,HttpSession session) {
		String userid=(String)session.getAttribute("userid");
		String username=(String)session.getAttribute("username");
		String sex=(String)session.getAttribute("sex");
		vo.setUserid(userid);
		vo.setUsername(username);
		vo.setSex(sex);
		
		service.commentReplyInsert(vo.getNo(), vo);
		return commnosListData(1, vo.getCno(), vo.getType());
	}
	// 삭제
	@GetMapping("comment/delete_vue.do")
	public Map comment_delete(CommentVO vo) {
		service.commentDelete(vo.getNo());
		return commnosListData(1, vo.getCno(), vo.getType());
	}
}
