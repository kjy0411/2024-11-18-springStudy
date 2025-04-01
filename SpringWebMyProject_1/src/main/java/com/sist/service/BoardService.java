package com.sist.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.sist.vo.BoardVO;
@Service
public interface BoardService {
	public List<BoardVO> boardListData(@Param("start") int start, @Param("end") int end);
	public int boardRowCount();
	public BoardVO boardDetailData(int no,int type);
	public void boardInsert(BoardVO vo);
	public boolean boardUpdate(BoardVO vo);
	public boolean boardDelete(int no,String pwd);
}
