package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;
/*  
 *  interface DB
 *  
 *  class A implementsDB
 *  class B implementsDB
 *  
 *  @Autowired
 *  @Qualifier(name="a") => 중복된 경우 특정 객체 지정
 *  DB db;
 */
@Repository
public class GoodsDAO {
	@Autowired
	private GoodsMapper mapper;
	
	public List<GoodsVO> busanGoodsListData(int start,int end){
		return mapper.busanGoodsListData(start, end);
	}
	public int busanGoodsTotalPage() {
		return mapper.busanGoodsTotalPage();
	}
	public GoodsVO busanGoodsDetailData(int no) {
		return mapper.busanGoodsDetailData(no);
	}
}
