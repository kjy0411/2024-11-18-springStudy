package com.sist.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.*;
import com.sist.vo.*;
public interface DataBoardMapper {
	// 인라인뷰 단점 => Top-N
	@Select("SELECT no,name,subject,hit,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,replycount,num "
			+ "FROM (SELECT no,name,subject,hit,regdate,replycount,rownum as num "
			+ "FROM (SELECT no,name,subject,hit,regdate,replycount "
			+ "FROM vueDataBoard ORDER BY no DESC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<DataBoardVO> dataBoardListData(Map map);
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM vueDataBoard")
	public int dataBoardTotalPage();
	@Insert("INSERT INTO vueDataBoard VALUES("
			+ "vb_no_seq.nextval,#{name},#{subject},#{content},#{pwd},SYSDATE,0,#{filename},#{filesize},#{filecount},0)")
	public void dataBoardInsert(DataBoardVO vo);
	@Update("UPDATE vueDataBoard SET hit=hit+1 WHERE no=#{no}")
	public void dataBoardHitIncrement(int no);
	@Select("SELECT no,name,subject,content,hit,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,filename,filesize,filecount "
			+ "FROM vueDataBoard WHERE no=#{no}")
	public DataBoardVO dataBoardDetailData(int no);
}
