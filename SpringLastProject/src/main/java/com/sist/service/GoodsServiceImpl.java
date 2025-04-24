package com.sist.service;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.*;
import com.sist.vo.*;
/* 
 *  요청 = Service = DAO = 오라클
 *  응답 = Service = DAO = 오라클
 *  				---------- 유지보수시에 클라이언트에 오류가 없게 처리 
 */
@Service
public class GoodsServiceImpl implements GoodsService{
	@Autowired
	private GoodsDAO gDao;
	@Autowired
	private CartDAO cDao;
	
	@Override
	public List<GoodsVO> busanGoodsListData(int start, int end) {
		return gDao.busanGoodsListData(start, end);
	}

	@Override
	public int busanGoodsTotalPage() {
		return gDao.busanGoodsTotalPage();
	}

	@Override
	public GoodsVO busanGoodsDetailData(int no) {
		return gDao.busanGoodsDetailData(no);
	}
	
}
