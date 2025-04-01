package com.sist.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.*;
import com.sist.vo.*;

public interface DataBoardMapper {
	@Select("SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,num "
			+ "FROM (SELECT no,subject,name,regdate,hit,rownum as num "
			+ "FROM (SELECT no,subject,name,regdate,hit "
			+ "FROM springDataBoard ORDER BY no DESC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<DataBoardVO> boardListData(@Param("start") int start, @Param("end") int end);
	@Select("SELECT COUNT(*) FROM springDataBoard")
	public int boardRowCount();
	@Insert("INSERT INTO springDataBoard VALUES("
			+ "sdb_no_seq.nextval,#{name},#{subject},#{content},#{pwd},SYSDATE,0,#{filecount})")
	public int boardInsert(DataBoardVO vo);
	@Select("SELECT sdb_no_seq.currval FROM DUAL")
	public int boardCurentNoData();
	@Update("UPDATE springDataBoard SET "
			+ "hit=hit+1 "
			+ "WHERE no=#{no}")
	public void hitIncrement(int no);
	@Select("SELECT * FROM springDataBoard WHERE no=#{no}")
	public DataBoardVO boardDetailData(int no);
	@Select("SELECT pwd FROM springDataBoard WHERE no=#{no}")
	public String boardGetPassword(int no);
	@Delete("DELETE FROM springDataBoard WHERE no=#{no}")
	public void databoardDelete(int no);
	@Select("SELECT filecount FROM springDataBoard WHERE no=#{no}")
	public int databoardFileCount(int no);
	@Update("UPDATE springDataBoard SET name=#{name},subject=#{subject},content=#{content} WHERE no=#{no}")
	public void databoardUpdate(DataBoardVO vo);
	/*  
	 *  매개변수 여러개 => INSERT, UPDATE, SELECT
	 *  							   ------ 조건 검색
	 *  
	 *  => 매개변수 처리 : 한개일 경우 => 입란 데이터 타입
	 *  	검색 : (String fd)
	 *  	페이지 : (int page) ...
	 *  => HashMap / VO / @Param
	 */
}
