package com.sist.service;

import java.util.*;

import com.sist.vo.BoardVO;

public interface BoardService {
	public List<BoardVO> boardListData(Map map);
	public int boardRowCount();
	public void boardInsert(BoardVO vo);
	/**
	 * @param vo
	 * @param type 0:유지 1:증가
	 */
	public BoardVO boardDetailData(int no,int type);
	public boolean boardCheckPassWord(BoardVO vo);
	public void boardUpdate(BoardVO vo);
	public void boardDelete(BoardVO vo);
}
