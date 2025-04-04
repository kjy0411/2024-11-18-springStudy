package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;

public interface BoardMapper {
	@Select("SELECT no,subject,name,hit,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,num "
			+ "FROM (SELECT no,subject,name,hit,regdate,rownum as num "
			+ "FROM (SELECT no,subject,name,hit,regdate "
			+ "FROM springReplyBoard ORDER BY no DESC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<BoardVO> boardListData(Map map);
	@Select("SELECT COUNT(*) FROM springReplyBoard")
	public int boardRowCount();
	@Insert("INSERT INTO springReplyBoard(no,name,subject,content,pwd,group_id) "
			+"VALUES(srb_no_seq.nextval,#{name},#{subject},#{content},#{pwd},(SELECT NVL(MAX(group_id)+1,1) FROM springReplyBoard))")
	public void boardInsert(BoardVO vo);
	@Update("UPDATE springReplyBoard SET hit=hit+1 WHERE no=#{no}")
	public void boardHitIncrement(int no);
	@Select("SELECT no,name,subject,content,hit,TO_CHAR(regdate,'YYYY-MM-DD') as dbday "
			+"FROM springReplyBoard WHERE no=#{no}")
	public BoardVO boardDetailData(int no);
	@Select("SELECT pwd FROM springReplyBoard WHERE no=#{no}")
	public String boardGetPassWord(int no);
	@Update("UPDATE springReplyBoard SET name=#{name},subject=#{subject},content=#{content} WHERE no=#{no}")
	public void boardUpdate(BoardVO vo);
	@Delete("DELETE FROM springReplyBoard WHERE no=#{no}")
	public void boardDelete(int no);
}
