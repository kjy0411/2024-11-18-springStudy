package com.sist.auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
/*  
 *  POJO방식을 주로 사용
 *  ------- 일반 자바 => 인터페이스/상속이 없는 독립된 클래스
 */
@Component
public class MainClass {
	// 실무 => @Autowird + @Qualifier = @Resource
	@Autowired
	// 반드시 스프링에서 메모리가 할당이 되는 경우에만 사용이 가능
	@Qualifier(value = "oracle")
	// 같은 형태의 클래스가 여러개인 경우 특정 객체를 지정
	private DAO dao;
	// DAO dao=new Oracle() / dao=new MySql()
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=new ClassPathXmlApplicationContext("app2.xml");
		MainClass mc=(MainClass)app.getBean("mainClass");
		mc.dao.connection();
	}

}
