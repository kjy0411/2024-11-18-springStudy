package com.sist.spring5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/*
 *  ���������� XML�� �о
 *  	1) �޸� �Ҵ�
 *  	2) Map�� ����
 *  	3) ���
 *  	4) ��ü �Ҹ�
 *  
 *  	�����̳� => ��ü�� ���� �ֱ� ����
 */
public class MainClass {
	
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("application.xml");
		// ����� (����) Ŭ���� ã�� => DL => getBean()
		Sawon sa=(Sawon)app.getBean("sawon");
		sa.display();
		
		Member me=(Member)app.getBean("member");
		me.display();
		
		Student st=(Student)app.getBean("std");
		st.display();
	}
	
}
