package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;
@Repository
public class CartDAO {
	@Autowired
	private GoodsMapper mapper;
	
	public void goodsCartInsert(CartVO vo) {
		mapper.goodsCartInsert(vo);
	}
	public int goodsCartGnoCount(CartVO vo) {
		return mapper.goodsCartGnoCount(vo);
	}
	public void goodsAccountUpdate(CartVO vo) {
		mapper.goodsAccountUpdate(vo);
	}
	public List<CartVO> goodsCartListData(String userid){
		return mapper.goodsCartListData(userid);
	}
	public List<CartVO> goodsBuyListData(String userid){
		return mapper.goodsBuyListData(userid);
	}
	public void goodsCartCancel(int cno) {
		mapper.goodsCartCancel(cno);;
	}
	public void goodsBuyUpdate(int cno) {
		mapper.goodsBuyUpdate(cno);
	}
}
