package com.sist.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.*;
import com.sist.vo.*;
public interface BoardMapper {
	//목록
	@Select("SELECT no,name,subject,hit,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,num "
			+ "FROM (SELECT no,name,subject,hit,regdate,rownum as num "
			+ "FROM (SELECT no,name,subject,hit,regdate "
			+ "FROM freeboard ORDER BY no DESC))"
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<BoardVO> boardListData(@Param("start") int start, @Param("end") int end);
	@Select("SELECT COUNT(*) FROM freeboard")
	public int boardRowCount();
	@Update("UPDATE freeboard SET hit=hit+1 WHERE no=#{no}")
	//상세보기
	public void boardHitIncrement(int no);
	@Select("SELECT no,name,subject,content,hit,TO_CHAR(regdate,'YYYY-MM-DD') as dbday "
			+ "FROM freeboard WHERE no=#{no}")
	public BoardVO boardDetailData(int no);
	//등록
	@Insert("INSERT INTO freeboard VALUES("
			+ "(SELECT NVL(MAX(no)+1,1) FROM freeboard),"
			+ "#{name},#{subject},#{content},#{pwd},SYSDATE,0)")
	public void boardInsert(BoardVO vo);
	//비밀번호 확인
	@Select("SELECT pwd FROM freeboard WHERE no=#{no}")
	public String boardGetPassword(int no);
	//수정
	@Update("UPDATE freeboard SET "
			+ "name=#{name},"
			+ "subject=#{subject},"
			+ "content=#{content} "
			+ "WHERE no=#{no}")
	public void boardUpdate(BoardVO vo);
	//삭제
	@Delete("DELETE FROM freeboard WHERE no=#{no}")
	public void boardDelete(int no);
}
