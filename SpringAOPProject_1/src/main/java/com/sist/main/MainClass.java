package com.sist.main;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;
/*  
 *  => Ʈ����� / ���� => �ڵ� ȣ��
 *  
 *  insert => ���� (�������)
 *  update => ���� 
 *  delete
 *  
 *  public void insert()
 *  {
 *  	try{
 *  		getConnection()
 *  		conn.setAutoCommit(false) *
 *  		..
 *  		..
 *  		conn.commit() *
 *  	}catch(){
 *  		conn.rollback()	*
 *  	}finally{
 *  		conn.setAutoCommit(true) *
 *  		disconnection()
 *  	}
 *  
 *  	@Transactional
 *  	public void insert(){
 *  		insert
 *  		insert
 *  	}
 *  }
 */
public class MainClass {

	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		BoardDAO dao=(BoardDAO)app.getBean("dao");
		dao.boardListData(1);
		dao.boardDetailDate("ȫ�浿");
		dao.boardInsert();
		dao.boardUpdate();
		dao.boardDelete();
		dao.print();
	}
	
}
