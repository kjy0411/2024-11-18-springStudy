package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;
import com.sist.commons.*;
import com.sist.vo.*;
import com.sist.service.CompanyService;

@Controller
public class CompanyController {
	@Autowired
	private CompanyService service;
	
	@GetMapping("com/list.do")
	public String com_list(String page, Model model) {
		int rowSize=12;
		final int BLOCK=10;
		int count=service.comRowCount();
		Map map=CommonsPagination.pageConfig(page, count, rowSize, BLOCK);
		List<CompanyVO> cList=service.comListData(map);
		
		model.addAttribute("cList",cList);
		model.addAttribute("curpage",map.get("curpage"));
		model.addAttribute("totalpage",map.get("totalpage"));
		model.addAttribute("startPage",map.get("startPage"));
		model.addAttribute("endPage",map.get("endPage"));
		model.addAttribute("main_jsp","../com/list.jsp");
		return "main/main";
	}
	@GetMapping("com/detail.do")
	public String com_detail(int cno, Model model) {
		CompanyVO cvo=service.comDetailData(cno, 0);
		model.addAttribute("cvo",cvo);
		model.addAttribute("main_jsp","../com/detail.jsp");
		return "main/main";
	}
}
