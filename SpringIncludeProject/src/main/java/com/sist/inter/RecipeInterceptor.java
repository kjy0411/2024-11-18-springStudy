package com.sist.inter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// 자동 로그인
/*  
 *  웹 개발
 *  1. Controller 역할, AOP 역할
 *  2. Task 역할 => 스케쥴러
 *  3. Interceptor => 중간에 필요한 부분에 기능 추가
 *  4. DAO / Service
 *  -----------------
 *  include => tiles
 *  보안 / betch / 통계
 *  			 ---- 파이썬 (Pandas,Numpy)
 *  					| Django
 *  Front : Vue => 디렉티브
 *  			   v-for v-if v-else v-model
 *  			   => 라이브러리 : jacksion
 *  
 *  
 *  main.do 요청
 *  	|
 *  DispatcherServlet => FrontController : 요청을 받아서 HandlerMapping 응답
 *  	|
 *  HandlerMapping => Model을 찾아서 메소드 호출 => @GetMapping / @PostMapping
 *  	| => preHandle
 *  메소드 호출
 *  	| => postHandle
 *  ViewResolver
 *  	| => afterCompletion
 *     JSP
 *  
 *  @Transaction => 하나라도 실패시 commit대신 rollback
 *  ---
 *  --- insert
 *  --- insert
 *  --- update
 *  	commit
 */
public class RecipeInterceptor extends HandlerInterceptorAdapter{
	// 자동 로그인 / ID저장 remember-me
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		return super.preHandle(request, response, handler);
	}
	// 
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
	
}
