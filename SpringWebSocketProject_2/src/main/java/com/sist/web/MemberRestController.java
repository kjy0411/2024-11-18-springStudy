package com.sist.web;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.dao.*;
import com.sist.vo.*;
@RestController
public class MemberRestController {
	@Autowired
	private MemberDAO dao;
	
	@PostMapping("member/login_vue.do")
	public String member_login(String id,String pwd,HttpSession session) {
		System.out.println(id);
		System.out.println(pwd);
		MemberVO vo=dao.memberLogin(id, pwd);
		if(vo.getMsg().equals("OK")) {
			session.setAttribute("id", vo.getId());
			session.setAttribute("name", vo.getName());
			session.setAttribute("sex", vo.getSex());
		}
		return vo.getMsg();
	}
	@GetMapping("member/logout.do")
	public void member_logout() {
	}
}
