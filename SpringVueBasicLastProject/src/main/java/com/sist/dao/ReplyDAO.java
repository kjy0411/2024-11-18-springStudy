package com.sist.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;
@Repository
public class ReplyDAO {
	@Autowired
	private ReplyMapper mapper;
	
	public List<ReplyVO> replyListData(int bno){
		return mapper.replyListData(bno);
	}
	public void replyInsert(ReplyVO vo) {
		mapper.replyInsert(vo);
	}
	public void replyUpdate(ReplyVO vo) {
		mapper.replyUpdate(vo);
	}
	public void replyDelete(int no) {
		ReplyVO vo=mapper.replyInfoData(no);
		mapper.replyDelete(vo);
	}
}
