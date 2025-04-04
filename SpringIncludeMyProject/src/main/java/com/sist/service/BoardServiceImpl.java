package com.sist.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardDAO bDao;

	@Override
	public List<BoardVO> boardListData(Map map) {
		return bDao.boardListData(map);
	}

	@Override
	public int boardRowCount() {
		return bDao.boardRowCount();
	}

	@Override
	public void boardInsert(BoardVO vo) {
		bDao.boardInsert(vo);
	}

	@Override
	public BoardVO boardDetailData(int no,int type) {
		return bDao.boardDetailData(no,type);
	}

	@Override
	public boolean boardCheckPassWord(BoardVO vo) {
		return bDao.boardCheckPassWord(vo);
	}

	@Override
	public void boardUpdate(BoardVO vo) {
		bDao.boardUpdate(vo);
	}

	@Override
	public void boardDelete(BoardVO vo) {
		bDao.boardDelete(vo);
	}

}
