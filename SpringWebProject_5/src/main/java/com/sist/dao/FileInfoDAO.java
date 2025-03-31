package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;
@Repository
public class FileInfoDAO {
	@Autowired
	private FileInfoMapper mapper;
	
	public void boardFileInsert(FileInfoVO vo) {
		mapper.boardFileInsert(vo);
	}
	public List<FileInfoVO> fileListData(int no){
		return mapper.fileListData(no);
	}
}
