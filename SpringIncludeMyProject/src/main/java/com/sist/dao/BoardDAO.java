package com.sist.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class BoardDAO {
	@Autowired
	private BoardMapper mapper;
	
	public List<BoardVO> boardListData(Map map){
		return mapper.boardListData(map);
	}
	public int boardRowCount() {
		return mapper.boardRowCount();
	}
	public void boardInsert(BoardVO vo) {
		mapper.boardInsert(vo);
	}
	public BoardVO boardDetailData(int no,int type) {
		if(type==1) {
			mapper.boardHitIncrement(no);
		}
		return mapper.boardDetailData(no);
	}
	public boolean boardCheckPassWord(BoardVO vo) {
		boolean bCheck=false;
		String db_pwd=mapper.boardGetPassWord(vo.getNo());
		if(db_pwd.equals(vo.getPwd())){
			bCheck=true;
		}
		return bCheck;
	}
	public void boardUpdate(BoardVO vo) {
		mapper.boardUpdate(vo);
	}
	public void boardDelete(BoardVO vo) {
		mapper.boardDelete(vo.getNo());
	}
}
