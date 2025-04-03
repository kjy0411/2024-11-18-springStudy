package com.sist.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.vo.*;
import com.sist.dao.*;
@Service // 여러개의 DAO를 통합해서 사용
/*  			@Controller
 *  요청 =======> Model <=======> Service <=======> DAO
 *  				|								|
 *  			ViewResolver					  리펙토링
 *  				|
 *  			   JSP
 */
public class RecipeServiceImpl implements RecipeService{
	@Autowired
	private RecipeDAO rDao;
	
	@Autowired
	private RecipeDetailDAO dDao;
	
	@Override
	public List<RecipeVO> recipeListData(Map map) {
		return rDao.recipeListData(map);
	}

	@Override
	public int recipeTotalPage() {
		return rDao.recipeTotalPage();
	}

	@Override
	public RecipeDetailVO recipeDetailData(int no) {
		return dDao.recipeDetailData(no);
	}

	@Override
	public List<RecipeVO> recipeFindData(Map map) {
		return rDao.recipeFindData(map);
	}

	@Override
	public int recipeFindTotalPage(String fd) {
		return rDao.recipeFindTotalPage(fd);
	}

	@Override
	public List<ChefVO> chefListData(Map map) {
		return rDao.chefListData(map);
	}

	@Override
	public int chefTotalPage() {
		return rDao.chefTotalPage();
	}

}
