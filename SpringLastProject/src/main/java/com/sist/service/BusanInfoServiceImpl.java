package com.sist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
@Service
public class BusanInfoServiceImpl implements BusanInfoService{
	@Autowired
	private BusanInfoDAO dao;

	@Override
	public List<BusanInfoVO> BusanInfoListData(Map map) {
		return dao.BusanInfoListData(map);
	}

	@Override
	public int BusanInfoTotalPage(int cno) {
		return dao.BusanInfoTotalPage(cno);
	}

	@Override
	public BusanInfoVO BusanInfoDetailData(int no) {
		return dao.BusanInfoDetailData(no);
	}
}
