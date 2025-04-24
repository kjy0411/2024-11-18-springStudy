package com.sist.dao;

import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class ReserveDAO {
	@Autowired
	public ReserveMapper mapper;
	
	public List<FoodVO> busanFoodReserveData(){
		return mapper.busanFoodReserveData();
	}
	public void reserveInsert(ReserveVO vo) {
		mapper.reserveInsert(vo);
	}
	public List<ReserveVO> reserveMyPageListData(String userid) {
		return mapper.reserveMyPageListData(userid);
	}
	public List<ReserveVO> reserveAdminPageListData() {
		return mapper.reserveAdminPageListData();
	}
	public void reserveDelete(int rno) {
		mapper.reserveDelete(rno);
	}
	public void reserveUpdate(int rno) {
		mapper.reserveUpdate(rno);
	}
}
