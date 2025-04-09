package com.sist.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;
// 스프링 관리하는 클래스 => 기능을 가지고 있는 클래스
// ~DAO, ~Manager, ~Service
@Repository
public class RecipeDAO {
	@Autowired
	private RecipeMapper mapper;
	
	public List<RecipeVO> recipeListData(int start,int end){
		return mapper.recipeListData(start, end);
	}
	public int recipeTotalPage() {
		return mapper.recipeTotalPage();
	}
	public List<RecipeVO> recipeFindListData(Map map){
		return mapper.recipeFindListData(map);
	}
	public int recipeFindTotalPage(String fd) {
		return mapper.recipeFindTotalPage(fd);
	}
	public RecipeDetailVO recipeDetailData(int no) {
		return mapper.recipeDetailData(no);
	}
}
