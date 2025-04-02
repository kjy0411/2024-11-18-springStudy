package com.sist.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.*;
import com.sist.dao.MovieDAO;
import com.sist.vo.*;
import com.sist.task.*;
@Aspect // => 공통 모듈 선언
@Component // => 메모리 할당
// request : @Controller, @RestController
// InterCeptor
public class RecipeAOP {
	@Autowired
	private MovieDAO mdao;
	
	@After("execution(* com.sist.web.*Controller.*(..))")
	public void after() {
		// 현재 사용중인 request를 가지고 온다
		HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		List<MovieVO> kList=mdao.movieListDate();
		request.setAttribute("kList", kList);
	}
	@Around("execution(* com.sist.web.*Controller.*(..))")
	public Object around(ProceedingJoinPoint jp) throws Throwable{
		Object obj=null;
		System.out.println("사용자 요청:"+jp.getSignature().getName());
		obj=jp.proceed();
		System.out.println("요청 처리 완료"+jp.getSignature().getName());
		return obj;
	}
}
