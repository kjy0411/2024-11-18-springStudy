package com.sist.commons;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 공통 예외처리 => Controller에만 적용
@ControllerAdvice // 메모리 할당
public class CommonsRecipe {
	@ExceptionHandler(Exception.class)
	public void exception(Exception ex) {
		System.out.println("========== 오류 발생 ==========");
		ex.printStackTrace();
	}
}
