package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

import com.sist.commons.CommonsPagination;
import com.sist.service.*;
import com.sist.vo.*;
@Controller
@RequestMapping("recipe/")
public class RecipeController {
	@Autowired
	private RecipeService service; // 자동 주입(주소값)
	
	@GetMapping("detail.do")
	public String recipe_detail(int no,Model model) {
		RecipeDetailVO vo=service.recipeDetailData(no);
		
		String data=vo.getFoodmake();
		String[] makes=data.split("\n");
		
		List<String> mList=new ArrayList<String>();
		List<String> iList=new ArrayList<String>();
		
		for(String line:makes) {
			StringTokenizer st=new StringTokenizer(line,"^");
			mList.add(st.nextToken());
			iList.add(st.nextToken());
		}
		
		model.addAttribute("mList",mList);
		model.addAttribute("iList",iList);
		model.addAttribute("vo",vo);
		model.addAttribute("main_jsp","../recipe/detail.jsp");
		return "main/main";
	}
	@RequestMapping("find.do")
	public String recipe_find(String page,String fd,Model model) {
		if(fd==null) {
			fd="";
		}else {
			fd=fd.trim();
		}
		
		int totalpage=service.recipeFindTotalPage(fd);
		
		Map map=CommonsPagination.pageConfig(page, totalpage, 12, 10);
		map.put("fd", fd);
		
		List<RecipeVO> rList=service.recipeFindData(map);
		int curpage=(int)map.get("curpage");
		int startPage=(int)map.get("startPage");
		int endPage=(int)map.get("endPage");
		
		model.addAttribute("rList",rList);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("fd",fd);
		model.addAttribute("main_jsp","../recipe/find.jsp");
		return "main/main";
	}
	@GetMapping("chef_list.do")
	public String recipe_chef_list(String page,Model model) {
		int totalpage=service.chefTotalPage();
		
		Map map=CommonsPagination.pageConfig(page, totalpage, 20, 10);
		List<ChefVO> cList=service.chefListData(map);
		int curpage=(int)map.get("curpage");
		int startPage=(int)map.get("startPage");
		int endPage=(int)map.get("endPage");
		
		model.addAttribute("cList",cList);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("main_jsp","../recipe/chef_list.jsp");
		return "main/main";
	}
}
