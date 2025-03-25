package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/*
	<bean id="dao" class="com.sist.dao.EmpDAO"
		p:mapper-ref="mapper"
	/>
 */
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.EmpMapper;
@Repository
public class EmpDAO {
	@Autowired
	private EmpMapper mapper;
	
	public List<EmpVO> empListData(){
		return mapper.empListData();
	}
	public EmpVO empdDetailData(int empno){
		return mapper.empDetailData(empno);
	}
}
