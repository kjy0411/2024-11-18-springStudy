package com.sist.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.*;
import com.sist.vo.*;
public interface CompanyMapper {
	@Select("SELECT cno,name,poster,num "
			+ "FROM (SELECT cno,name,poster,rownum as num "
			+ "FROM (SELECT cno,name,poster "
			+ "FROM company ORDER BY cno ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<CompanyVO> comListData(Map map);
	@Select("SELECT COUNT(*) FROM company")
	public int comRowCount();
	@Update("UPDATE company SET hit=hit+1 WHERE cno=#{cno}")
	public void comHitIncrement(int cno);
	@Select("SELECT cid,name,introduction,TO_CHAR(estdate,'YYYY-MM-DD') as dbestdate,estdate,c_type,ecount,"
			+ "TRUNC(take/1000000000000) as jo,"
			+ "TRUNC(MOD(take,1000000000000)/100000000) as uk,"
			+ "TRUNC(MOD(take,100000000)/10000) as man,"
			+ "industry,representative,homepage,bu_details,address,history,access_key,poster,sample,fo_count,cno "
			+ "FROM company "
			+ "WHERE cno=#{cno}")
	public CompanyVO comDetailData(int cno);
}
