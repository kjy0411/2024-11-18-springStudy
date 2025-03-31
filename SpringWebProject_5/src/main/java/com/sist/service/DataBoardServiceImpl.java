package com.sist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sist.dao.DataBoardDAO;
import com.sist.dao.FileInfoDAO;
import com.sist.vo.DataBoardVO;
import com.sist.vo.FileInfoVO;

@Component
public class DataBoardServiceImpl implements DataBoardService{
	@Autowired
	private DataBoardDAO dDao;
	@Autowired
	private FileInfoDAO fDao;
	
	@Override
	public List<DataBoardVO> boardListData(int start, int end) {
		return dDao.boardListData(start, end);
	}

	@Override
	public int boardRowCount() {
		return dDao.boardRowCount();
	}

	@Override
	public int boardInsert(DataBoardVO vo) {
		return dDao.boardInsert(vo);
	}

	@Override
	public void boardFileInsert(FileInfoVO vo) {
		fDao.boardFileInsert(vo);
	}

	@Override
	public DataBoardVO boardDetailData(int no) {
		return dDao.boardDetailData(no);
	}

	@Override
	public List<FileInfoVO> fileListData(int no) {
		return fDao.fileListData(no);
	}

}
