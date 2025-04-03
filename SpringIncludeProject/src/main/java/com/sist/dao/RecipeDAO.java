package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.ChefVO;
import com.sist.vo.RecipeVO;
import com.sist.mapper.*;
@Repository
public class RecipeDAO {
	@Autowired
	private RecipeMapper mapper;
	
	public List<RecipeVO> recipeListData(Map map){
		return mapper.recipeListData(map);
	}
	public int recipeTotalPage() {
		return mapper.recipeTotalPage();
	}
	public List<RecipeVO> recipeFindData(Map map){
		return mapper.recipeFindData(map);
	}
	public int recipeFindTotalPage(String fd) {
		return mapper.recipeFindTotalPage(fd);
	}
	public List<ChefVO>chefListData(Map map){
		return mapper.chefListData(map);
	}
	public int chefTotalPage() {
		return mapper.chefTotalPage();
	}
}
