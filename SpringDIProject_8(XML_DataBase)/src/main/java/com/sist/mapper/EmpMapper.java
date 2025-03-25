package com.sist.mapper;

import org.apache.ibatis.annotations.Select;
import java.util.*;
import com.sist.vo.*;
public interface EmpMapper {
	// 목록 출력
	@Select("SELECT empno,ename,job,hiredate,sal "
			+ "FROM emp")
	public List<EmpVO> empListData();
	@Select("SELECT empno,ename,job,hiredate,sal "
			+ "FROM emp "
			+ "WHERE empno=#{empno}")
	public EmpVO empDetailData(int empno);
	//	   resultType	id		prarmeterType
	//		EmpVO	empDetailData	int
	
	// @Insert @Update @Delete
}
