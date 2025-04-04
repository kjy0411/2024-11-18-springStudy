package com.sist.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.*;
import com.sist.vo.CompanyVO;

@Service
public class CompanyServiceImpl implements CompanyService{
	@Autowired
	private CompanyDAO cDao;
	@Autowired
	private EmploymentDAO eDao;
	@Override
	
	public List<CompanyVO> comListData(Map map) {
		return cDao.comListData(map);
	}
	@Override
	public int comRowCount() {
		return cDao.comRowCount();
	}
	@Override
	public CompanyVO comDetailData(int cno, int type) {
		if(type==1) {
			cDao.comHitIncrement(cno);
		}
		return cDao.comDetailData(cno);
	}
	@Override
	public List<CompanyVO> comFindListData(Map map) {
		return cDao.comFindListData(map);
	}
	@Override
	public int comFindRowCount(String fd) {
		return cDao.comFindRowCount(fd);
	}
}
