package com.sist.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.vo.*;
import com.sist.dao.*;

@Service
public class ReserveServiceImpl implements ReserveService{
	@Autowired
	private ReserveDAO rDao;
	@Override
	public List<FoodVO> busanFoodReserveData() {
		return rDao.busanFoodReserveData();
	}
	@Override
	public void reserveInsert(ReserveVO vo) {
		rDao.reserveInsert(vo);
	}
	@Override
	public List<ReserveVO> reserveMyPageListData(String userid) {
		return rDao.reserveMyPageListData(userid);
	}
	@Override
	public List<ReserveVO> reserveAdminPageListData() {
		return rDao.reserveAdminPageListData();
	}
	@Override
	public void reserveDelete(int rno) {
		rDao.reserveDelete(rno);
	}
	@Override
	public void reserveUpdate(int rno) {
		rDao.reserveUpdate(rno);
	}

}
