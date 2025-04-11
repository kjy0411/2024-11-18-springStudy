package com.sist.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.*;
import com.sist.vo.*;
public interface ReplyMapper {
	@Select("SELECT no,bno,id,name,msg,group_tab,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday "
			+ "FROM vueReply "
			+ "WHERE bno=#{bno} "
			+ "ORDER BY group_id DESC,group_step ASC")
	public List<ReplyVO> replyListData(int bno);
	@SelectKey(keyProperty = "no",resultType = int.class,before = true,
			statement = "SELECT NVL(MAX(no)+1,1) FROM vueReply")
	@Insert("INSERT INTO vueReply(no,bno,id,name,msg,group_id) "
			+ "VALUES(#{no},#{bno},#{id},#{name},#{msg},(SELECT NVL(MAX(group_id)+1,1) FROM vueReply))")
	public void replyInsert(ReplyVO vo);
	
}
