package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class VueDAO {
	@Autowired
	private VueMapper mapper;
	
	public List<FoodVO> foodListData(Map map){
		return mapper.foodListData(map);
	}
	public int foodTotalPage() {
		return mapper.foodTotalPage();
	}
	public FoodVO foodDetailData(int fno) {
		return mapper.foodDetailData(fno);
	}
	public List<GoodsVO> goodsListData(Map map){
		return mapper.goodsListData(map);
	}
	public int goodsTotalPage() {
		return mapper.goodsTotalPage();
	}
	public GoodsVO goodsDetailData(int no) {
		return mapper.goodsDetailData(no);
	}
}
