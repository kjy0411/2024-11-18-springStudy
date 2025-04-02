package com.sist.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MusicAOP {
	@After("execution(* com.sist.web.*Controller.*(..))")
	public static void musicPrint() {
	}
}
