package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.mapper.DataBoardMapper;
import com.sist.vo.DataBoardVO;

@Repository
public class DataBoardDAO {
	@Autowired
	private DataBoardMapper mapper;
	
	public List<DataBoardVO> boardListData(int start, int end){
		return mapper.boardListData(start, end);
	}
	public int boardRowCount() {
		return mapper.boardRowCount();
	}
	public int boardInsert(DataBoardVO vo) {
		mapper.boardInsert(vo);
		return mapper.boardRowCount();
	}
	public DataBoardVO boardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.boardDetailData(no);
	}
	public String boardGetPassword(int no) {
		return mapper.boardGetPassword(no);
	}
	public void databoardDelete(int no) {
		mapper.databoardDelete(no);
	}
	public int databoardFileCount(int no) {
		return mapper.databoardFileCount(no);
	}
	public DataBoardVO boardUpdateData(int no) {
		return mapper.boardDetailData(no);
	}
	public void databoardUpdate(DataBoardVO vo) {
		mapper.databoardUpdate(vo);
	}
}
