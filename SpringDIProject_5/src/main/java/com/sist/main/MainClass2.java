package com.sist.main;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.goods.*;
import com.sist.member.*;
import com.sist.sawon.*;
public class MainClass2 {

	public static void main(String[] args) {
		//등록한 XML을 컨테이너로 전송
		ApplicationContext app=new ClassPathXmlApplicationContext("app1.xml");
		// 클래스 찾기 => 활용
		SawonManager sa=(SawonManager)app.getBean("sa");
		sa.display();
		MemberManager mem=(MemberManager)app.getBean("memberManager");
		mem.display();
		GoodsManager goods=(GoodsManager)app.getBean("goods",GoodsManager.class);
		goods.display();
	}
	
}
