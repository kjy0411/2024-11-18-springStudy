package com.sist.main;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;
/*  
 *  => 트랜잭션 / 보안 => 자동 호출
 *  
 *  insert => 에러 (상관없다)
 *  update => 에러 
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
		dao.boardDetailDate("홍길동");
		dao.boardInsert();
		dao.boardUpdate();
		dao.boardDelete();
		dao.print();
	}
	
}
