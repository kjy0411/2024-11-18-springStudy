package com.sist.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.*;
import com.sist.vo.*;
public interface EmpMapper {
	
	@Select("SELECT ename,job,sal FROM emp")
	public List<EmpVO> empListData();
	
}
