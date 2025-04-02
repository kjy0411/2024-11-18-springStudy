package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class RecipeDetailDAO {
	@Autowired
	private RecipeDetailMapper mapper;
	public RecipeDetailVO recipeDetailData(int no) {
		return mapper.recipeDetailData(no);
	}
}
