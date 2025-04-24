package com.sist.service;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.*;
import com.sist.vo.*;
@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentDAO cDao;

	@Override
	public List<CommentVO> commentListData(Map map) {
		return cDao.commentListData(map);
	}

	@Override
	public int commentTotalPage(int cno, int type) {
		return cDao.commentTotalPage(cno, type);
	}

	@Override
	public void commentInsert(CommentVO vo) {
		cDao.commentInsert(vo);
	}

	@Override
	public void commentUpdate(String msg, int no) {
		cDao.commentUpdate(msg, no);
	}

	@Override
	public void commentReplyInsert(int pno, CommentVO vo) {
		cDao.commentReplyInsert(pno, vo);
	}

	@Override
	public void commentDelete(int no) {
		cDao.commentDelete(no);
	}
}
