package com.sist.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*  
 *  ���� ���� => ���� http://localhost:8080/web/board/list.do
 *  -------
 *  					Controller
 *  ����� ��û (.do) == DispatcherServlet
 *  						|
 *  					Model ã�� (@RequestMapping) : HandlerMapping
 *  						| request
 *  					Model ó�� => �޼ҵ� ȣ��
 *  						|
 *  					JSP ã�� : ViewResolver => ��θ� / Ȯ����
 *  								p:prefix="/"
 *  								p:suffix=".jsp"
 *  						| => request ����
 *  						JSP
 *  			HandlerMapping => DispatcherServlet
 */
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardModel {
	@RequestMapping("board/list.do")
	public String board_list(HttpServletRequest request,HttpServletResponse response) {
		String msg="Hello Spring MVC!!";
		request.setAttribute("msg", msg);
		return "board/list"; // .jsp(X)
	}
}
