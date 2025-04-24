package com.sist.service;
import java.util.*;
import com.sist.vo.*;
public interface GoodsService {
	
	public List<GoodsVO> busanGoodsListData(int start,int end);
	public int busanGoodsTotalPage() ;
	public GoodsVO busanGoodsDetailData(int no);
	
	public void goodsCartInsert(CartVO vo);
	public List<CartVO> goodsCartListData(String userid);
	public List<CartVO> goodsBuyListData(String userid);
	public void goodsCartCancel(int cno);
	public void goodsBuyUpdate(int cno);
}
