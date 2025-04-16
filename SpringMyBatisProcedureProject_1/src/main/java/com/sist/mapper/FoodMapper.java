package com.sist.mapper;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import java.util.*;
import com.sist.vo.*;
public interface FoodMapper {
	//<select id="foodTotalPage" resultType="int">
	public int foodTotalPage();
	
	/*  
		CREATE OR REPLACE PROCEDURE foodListData(
		    pStart NUMBER,
		    pEnd NUMBER,
		    pResult OUT SYS_REFCURSOR
		)
		IS
		-- 지역변수
		BEGIN
		    OPEN pResult FOR
		        SELECT fno,poster,name,num
		        FROM (SELECT fno,poster,name,rownum as num 
		        FROM (SELECT fno,poster,name 
		        FROM project_food ORDER BY fno ASC))
		        WHERE num BETWEEN pStart AND pEnd;
		END;
		/
	 */
	@Select(value = "{CALL foodListData("
			+ "#{pStart,mode=IN,javaType=java.lang.Integer},"
			+ "#{pEnd,mode=IN,javaType=java.lang.Integer},"
			+ "#{pResult,mode=OUT,jdbcType=CURSOR,resultMap=foodMap})}")
	@Options(statementType = StatementType.CALLABLE)
	public List<FoodVO> foodListData(Map map);
}
