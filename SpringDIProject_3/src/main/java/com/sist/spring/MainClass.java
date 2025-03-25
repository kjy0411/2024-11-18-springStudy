package com.sist.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		//String[] xml= {"app-member.xml","app-sawon.xml","app-student.xml"};
		ApplicationContext app=new ClassPathXmlApplicationContext("app-*.xml");
		Member mem=(Member)app.getBean("mem");
		System.out.println(mem.getMno()+" "+mem.getName()+" "+mem.getAddress());
		Sawon sa=(Sawon)app.getBean("sa");
		System.out.println(sa.getSabun()+" "+sa.getName()+" "+sa.getDept());
		Student std=(Student)app.getBean("std");
		System.out.println(std.getHakbun()+" "+std.getName()+" "+std.getSubject());
	}
	
}
