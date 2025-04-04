package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import com.sist.commons.*;
import com.sist.vo.*;
import com.sist.service.CompanyService;

@Controller
@RequestMapping("com/")
public class CompanyController {
	@Autowired
	private CompanyService service;
	
	@GetMapping("list.do")
	public String com_list(String page, Model model) {
		int rowSize=12;
		final int BLOCK=10;
		int count=service.comRowCount();
		Map map=CommonsPagination.pageConfig(page, count, rowSize, BLOCK);
		List<CompanyVO> cList=service.comListData(map);
		
		model.addAttribute("cList",cList);
		model.addAttribute("main_jsp","../com/list.jsp");
		return "main/main";
	}
	@GetMapping("detail.do")
	public String com_detail(int cno, Model model) {
		CompanyVO cvo=service.comDetailData(cno, 0);
		model.addAttribute("cvo",cvo);
		model.addAttribute("main_jsp","../com/detail.jsp");
		return "main/main";
	}
	@RequestMapping("find.do")
	public String com_find(String page,String fd, Model model) {
		int rowSize=16;
		final int BLOCK=10;
		if(fd==null)
			fd="";
		int count=service.comFindRowCount(fd);
		Map map=CommonsPagination.pageConfig(page, count, rowSize, BLOCK);
		map.put("fd", fd);
		List<CompanyVO> cList=service.comFindListData(map);
		
		model.addAttribute("cList",cList);
		model.addAttribute("fd",fd);
		model.addAttribute("main_jsp","../com/find.jsp");
		return "main/main";
	}
}
