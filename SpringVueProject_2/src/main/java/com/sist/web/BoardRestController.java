package com.sist.web;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

import oracle.net.aso.b;
@RestController
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	
	@GetMapping("/board/list_vue/{page}")
	public ResponseEntity<Map> baord_list(@PathVariable("page") int page){
		Map map=new HashMap();
		try {
			int rowSize=10;
			int start=(page-1)*rowSize+1;
			int end=page*rowSize;
			map.put("start", start);
			map.put("end", end);
			List<BoardVO> list=dao.boardListData(map);
			int totalpage=dao.boardTotalPage();
			
			final int BLOCK=10;
			int startPage=((page-1)/BLOCK*BLOCK)+1;
			int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
			
			if(endPage>totalpage) endPage=totalpage;
			
			map=new HashMap();
			map.put("list", list);
			map.put("curpage", page);
			map.put("totalpage", totalpage);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
			map.put("msg", "PathVariable를 이용한 게시판");
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@PostMapping("/board/insert_vue")
	public ResponseEntity<Map> boardInsert(@RequestBody BoardVO vo){
		Map map=new HashMap();
		try {
			dao.boardInsert(vo);
			map.put("msg", "yes");
		} catch (Exception e) {
			map.put("msg", "no");
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
		
	}
	@GetMapping("/board/detail_vue/{no}")
	public ResponseEntity<BoardVO> baord_detail(@PathVariable("no") int no){
		BoardVO vo=new BoardVO();
		try {
			vo=dao.boardDetailData(no);
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(vo,HttpStatus.OK);
	}
	@GetMapping("/board/update_vue/{no}")
	public ResponseEntity<BoardVO> baord_update(@PathVariable("no") int no){
		BoardVO vo=new BoardVO();
		try {
			vo=dao.boardUpdateData(no);
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(vo,HttpStatus.OK);
	}
	@PutMapping("/board/update_ok_vue")
	public ResponseEntity<Map> baord_update_ok(@RequestBody BoardVO vo){
		Map map=new HashMap();
		try {
			String msg=dao.boardUpdate(vo);
			map.put("msg", msg);
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@DeleteMapping("/board/delete_vue/{no}/{pwd}")
	public ResponseEntity<Map> baord_delete_ok(@PathVariable("no") int no, @PathVariable("pwd") String pwd){
		Map map=new HashMap();
		try {
			String msg=dao.boardDelete(no,pwd);
			map.put("msg", msg);
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
