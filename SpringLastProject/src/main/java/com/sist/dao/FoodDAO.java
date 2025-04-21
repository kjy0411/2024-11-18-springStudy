package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	public List<FoodVO> busanFoodListData(int start,int end){
		return mapper.busanFoodListData(start, end);
	}
	public int busanFoodTotalPage() {
		return mapper.busanFoodTotalPage();
	}
	public FoodVO busanFoodDetailData(int fno) {
		mapper.hitIncrement(fno);
		return mapper.busanFoodDetailData(fno);
	}
}
