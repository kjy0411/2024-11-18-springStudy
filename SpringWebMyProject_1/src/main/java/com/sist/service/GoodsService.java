package com.sist.service;

import java.util.*;

import com.sist.vo.GoodsVO;

public interface GoodsService {
	public List<GoodsVO> goodsListData(Map map);
	public int goodsTotalPage();
	public GoodsVO goodsDetailData(int no);
}
