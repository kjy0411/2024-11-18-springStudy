package com.sist.service;
import java.util.*;

import com.sist.vo.*;
public interface CommentService {
	public List<CommentVO> commentListData(Map map);
	public int commentTotalPage(int cno,int type);
	public void commentInsert(CommentVO vo);
	public void commentUpdate(String msg,int no);
	public void commentReplyInsert(int pno,CommentVO vo);
	public void commentDelete(int no);
}
