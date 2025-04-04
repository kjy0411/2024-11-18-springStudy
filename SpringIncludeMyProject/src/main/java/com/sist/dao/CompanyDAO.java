package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.CompanyMapper;
import com.sist.vo.CompanyVO;

@Repository
public class CompanyDAO {
	@Autowired
	private CompanyMapper mapper;
	
	public List<CompanyVO> comListData(Map map){
		return mapper.comListData(map);
	}
	public int comRowCount() {
		return mapper.comRowCount();
	}
	public void comHitIncrement(int cno) {
		mapper.comHitIncrement(cno);
	}
	public CompanyVO comDetailData(int cno) {
		return mapper.comDetailData(cno);
	}
	public List<CompanyVO> comFindListData(Map map){
		return mapper.comFindListData(map);
	}
	public int comFindRowCount(String fd) {
		return mapper.comFindRowCount(fd);
	}
}
