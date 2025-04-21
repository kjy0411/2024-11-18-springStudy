package com.sist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
// 의존성을 낮게 / DAO여러개를 통합해서 사용
/*  
 *  => JSP Header
 *     ---------- 요청 (메뉴)
 *     목록에서 링크
 *     	 |
 *     Mapper : SQL
 *       |
 *      DAO
 *       |
 *     Service
 *       |
 *     ServiceImpl
 *       |
 *     Controller / RestController
 *       |
 *     JSP 화면 출력
 */
@Service
public class FoodServiceImpl implements FoodService{
	@Autowired
	private FoodDAO fDao;
	
	@Override
	public List<FoodVO> busanFoodListData(int start, int end) {
		return fDao.busanFoodListData(start, end);
	}

	@Override
	public int busanFoodTotalPage() {
		return fDao.busanFoodTotalPage();
	}

	@Override
	public FoodVO busanFoodDetailData(int fno) {
		return fDao.busanFoodDetailData(fno);
	}

}
