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
/*  
 *  Model => 요청값만 받아서 JSP 전송하는 역할
 *  데이터베이스 여러개 => DAO : 단독처리, Service에 묶는 작업
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

	@Override
	public void goodsCartInsert(CartVO vo) {
		int count=cDao.goodsCartGnoCount(vo);
		if(count==0) {
			cDao.goodsCartInsert(vo);
		}else {
			cDao.goodsAccountUpdate(vo);
		}
	}

	@Override
	public List<CartVO> goodsCartListData(String userid) {
		return cDao.goodsCartListData(userid);
	}

	@Override
	public List<CartVO> goodsBuyListData(String userid) {
		return cDao.goodsBuyListData(userid);
	}

	@Override
	public void goodsCartCancel(int cno) {
		cDao.goodsCartCancel(cno);
	}

	@Override
	public void goodsBuyUpdate(int cno) {
		cDao.goodsBuyUpdate(cno);
	}
	
}
