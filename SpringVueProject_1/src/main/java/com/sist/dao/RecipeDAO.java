package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class RecipeDAO {
	@Autowired
	private RecipeMapper mapper;
	public List<RecipeVO> recipeListData(int start, int end){
		return mapper.recipeListData(start, end);
	}
	public int recipeRowCount() {
		return mapper.recipeRowCount();
	}
	public RecipeDetailVO recipeDetailData(int no) {
		return mapper.recipeDetailData(no);
	}
}
