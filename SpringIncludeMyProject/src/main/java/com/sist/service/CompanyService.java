package com.sist.service;

import java.util.List;
import java.util.Map;

import com.sist.vo.CompanyVO;

public interface CompanyService {
	public List<CompanyVO> comListData(Map map);
	public int comRowCount();
	/**
	 * @param cno
	 * @param type 0:유지 1:증가
	 */
	public CompanyVO comDetailData(int cno,int type);
}
