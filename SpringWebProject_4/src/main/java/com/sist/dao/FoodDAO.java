package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	public List<FoodVO> foodListData(Map map) {
		return mapper.foodListData(map);
	}
	
	public FoodVO foodDetailData(int fno) {
		mapper.hitIncrement(fno);
		return mapper.foodDetailData(fno);
	}
	public int foodTotalPage() {
		return mapper.foodTotalPage();
	}
}
