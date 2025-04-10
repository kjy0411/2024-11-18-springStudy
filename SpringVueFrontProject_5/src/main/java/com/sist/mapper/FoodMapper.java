package com.sist.mapper;

import org.apache.ibatis.annotations.Select;
import java.util.*;
import com.sist.vo.*;
public interface FoodMapper {
	@Select("SELECT fno,poster,name,num "
		+ "FROM (SELECT fno,poster,name,rownum as num "
		+ "FROM (SELECT fno,poster,name "
		+ "FROM project_food WHERE type LIKE '%'||#{type}||'%' ORDER BY fno)) "
		+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodListData(Map map);
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM project_food WHERE type LIKE '%'||#{type}||'%'")
	public int foodTotalPage(String type);
	@Select("SELECT fno,poster,name,num "
			+ "FROM (SELECT fno,poster,name,rownum as num "
			+ "FROM (SELECT fno,poster,name "
			+ "FROM project_food WHERE address LIKE '%'||#{fd}||'%' ORDER BY fno)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodFindListData(Map map);
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM project_food "
			+ "WHERE address LIKE '%'||#{fd}||'%'")
	public int foodFindTotalPage(String fd);
	@Select("SELECT * FROM project_food WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
}
