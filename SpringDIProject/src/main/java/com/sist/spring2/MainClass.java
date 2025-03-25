package com.sist.spring2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import java.util.*;
public class MainClass {
	
	public static void main(String[] args) {
		ApplicationContext app=new GenericXmlApplicationContext("app4.xml");
		EmpDAO dao=(EmpDAO)app.getBean("dao");
		List<EmpVO> list=dao.empAllData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEname()+" "+vo.getDbday()+" "+vo.getSal());
		}
	}
	
}
