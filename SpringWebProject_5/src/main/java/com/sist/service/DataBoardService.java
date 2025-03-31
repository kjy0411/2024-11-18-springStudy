package com.sist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.vo.DataBoardVO;
import com.sist.vo.FileInfoVO;

@Service
public interface DataBoardService {
	
	public List<DataBoardVO> boardListData(int start, int end);
	public int boardRowCount();
	public int boardInsert(DataBoardVO vo);
	public void boardFileInsert(FileInfoVO vo);
	public DataBoardVO boardDetailData(int no);
	public List<FileInfoVO> fileListData(int no);
}
