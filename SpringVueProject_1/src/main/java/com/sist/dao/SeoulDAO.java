package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class SeoulDAO {
	@Autowired
	private SeoulMapper mapper;
	
	public List<SeoulVO> seoulListData(Map map){
		return mapper.seoulListData(map);
	}
	public int seoulTotalPage(Map map) {
		return mapper.seoulTotalPage(map);
	}
	public SeoulVO seoulDetailData(Map map) {
		return mapper.seoulDetailData(map);
	}
	public List<FoodVO> foodRecommandData(String address){
		return mapper.foodRecommandData(address);
	}
}
