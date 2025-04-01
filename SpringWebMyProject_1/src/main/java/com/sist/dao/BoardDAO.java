package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.BoardMapper;
import com.sist.vo.BoardVO;

@Repository
public class BoardDAO {
	@Autowired
	private BoardMapper mapper;
	
	public List<BoardVO> boardListData(@Param("start") int start, @Param("end") int end){
		return mapper.boardListData(start, end);
	}
	public int boardRowCount() {
		return mapper.boardRowCount();
	}
	public BoardVO boardDetailData(int no,int type) {
		if(type==0) {
			mapper.boardHitIncrement(no);
		}
		return mapper.boardDetailData(no);
	}
	public void boardInsert(BoardVO vo) {
		mapper.boardInsert(vo);
	}
	public boolean boardUpdate(BoardVO vo) {
		boolean check=false;
		String db_pwd=mapper.boardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			mapper.boardUpdate(vo);
			check=true;
		}
		return check;
	}
	public boolean boardDelete(int no,String pwd) {
		boolean check=false;
		String db_pwd=mapper.boardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			mapper.boardDelete(no);
			check=true;
		}
		return check;
	}
}
