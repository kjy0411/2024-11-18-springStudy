package com.sist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;
@Component
public class BoardServiceImpl implements BoardService{
	@Autowired
	public BoardDAO bDao;

	@Override
	public List<BoardVO> boardListData(int start, int end) {
		return bDao.boardListData(start, end);
	}

	@Override
	public int boardRowCount() {
		return bDao.boardRowCount();
	}

	@Override
	public BoardVO boardDetailData(int no,int type) {
		return bDao.boardDetailData(no,type);
	}

	@Override
	public void boardInsert(BoardVO vo) {
		bDao.boardInsert(vo);
	}

	@Override
	public boolean boardUpdate(BoardVO vo) {
		return bDao.boardUpdate(vo);
	}

	@Override
	public boolean boardDelete(int no,String pwd) {
		return bDao.boardDelete(no,pwd);
	}

}
