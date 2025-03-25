package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.EmpVO;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		EmpDAO dao=(EmpDAO)app.getBean("empDAO");
		List<EmpVO> list=dao.empListData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "+vo.getEname()+" "+vo.getJob()+" "+vo.getHiredate()+" "+vo.getSal());
		}
		EmpVO vo=dao.empdDetailData(7788);
		System.out.println("°Ë»ö(7788):"+vo.getEmpno()+" "+vo.getEname()+" "+vo.getJob()+" "+vo.getHiredate()+" "+vo.getSal());
	}

}
