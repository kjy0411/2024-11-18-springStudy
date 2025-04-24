package com.sist.service;
import java.util.*;
import com.sist.vo.*;
public interface GoodsService {
	
	public List<GoodsVO> busanGoodsListData(int start,int end);
	public int busanGoodsTotalPage() ;
	public GoodsVO busanGoodsDetailData(int no);
}
