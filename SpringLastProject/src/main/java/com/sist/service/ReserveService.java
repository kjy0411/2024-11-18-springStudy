package com.sist.service;

import java.util.*;

import com.sist.vo.*;

public interface ReserveService {
	public List<FoodVO> busanFoodReserveData();
	public void reserveInsert(ReserveVO vo);
	public List<ReserveVO> reserveMyPageListData(String userid);
	public List<ReserveVO> reserveAdminPageListData();
	public void reserveDelete(int rno);
	public void reserveUpdate(int rno);
}
