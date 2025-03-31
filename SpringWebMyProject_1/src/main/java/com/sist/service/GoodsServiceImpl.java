package com.sist.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
@Service
public class GoodsServiceImpl implements GoodsService{
	@Autowired
	private GoodsDAO gDao;
	
	@Override
	public List<GoodsVO> goodsListData(Map map) {
		return gDao.goodsListData(map);
	}

	@Override
	public int goodsTotalPage() {
		return gDao.goodsTotalPage();
	}

	@Override
	public GoodsVO goodsDetailData(int no) {
		return gDao.goodsDetailData(no);
	}

}
