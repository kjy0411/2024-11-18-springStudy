package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.sist.dao.*;
@Controller
public class MainController {
	@Autowired
	private MusicDAO dao;
	
	@GetMapping("main/main.do")
	public String main_main(Model model) {
		model.addAttribute("main_jsp","../main/home.jsp");
		return "main/main";
	}
}
