package com.sist.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

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
	@Update("UPDATE vueReply SET msg=#{msg} WHERE no=#{no}")
	public void replyUpdate(ReplyVO vo);
	@Select("SELECT no,group_id,group_step "
			+ "FROM vueReply WHERE no=#{no}")
	public ReplyVO replyInfoData(int no);
	@Delete("DELETE FROM vueReply WHERE group_id=#{group_id} AND group_step>=#{group_step}")
	public void replyDelete(ReplyVO vo);
	
	//대댓글 순서(트랜잭션)
	// 1. 상위 => groyp_id,group_step,group_tab
	@Select("SELECT group_id,group_step,group_tab "
			+ "FROM vueReply "
			+ "WHERE no=#{no}")
	public ReplyVO replyParentInfoData(int no);
	// 2. 출력순서 변경(group_step)
	@Update("UPDATE vueReply SET group_step=group_step+1 WHERE group_id=#{group_id} AND group_step>#{group_step}")
	public void replyGroupStepIncrement(ReplyVO vo);
	// 3. INSERT
	@Insert("INSERT INTO vueReply(no,bno,id,name,msg,group_id,group_step,group_tab) "
			+ "VALUES((SELECT NVL(MAX(no)+1,1) FROM vueReply),#{bno},#{id},#{name},#{msg},#{group_id},#{group_step},#{group_tab})")
	public void replyReplyInsert(ReplyVO vo);
	/*  
	 *  				gi	gs	gt
	 *  AAAAA			1	0	0
	 *    =>DDDDD		1	1	1
	 *    =>BBBBB		1	2	1
	 *      =>CCCCC		1	3	2
	 */
}
