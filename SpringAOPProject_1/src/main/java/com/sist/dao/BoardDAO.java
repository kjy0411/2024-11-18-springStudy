package com.sist.dao;
/*  
 *  ���ռ��� ���� ���α׷� => �������� �����ڰ� ���� �����ڰ� ���� ����
 *  ---------------- Container / POJO / DI
 *  									| Ŭ���� �޸� �Ҵ�� �ʿ��� ��쿡 ������� �ʱ�ȭ
 *  							 | �ٸ� Ŭ������ ������ ���� ���� Ŭ����
 *  								=> �������̽� / ����� ������� �ʴ� Ŭ����
 *  				 | Ŭ������ ������ ��Ƽ� ����
 *  => ���� �ҽ��� ���� �߻� => �������� ���Ǵ� �ҽ��� ��Ƽ� ����
 *  					   ===========================
 *  						| ������ => AOP (Ⱦ������ ���α׷�)
 *  							=> �ݺ� �ڵ��� ���� �ʴ´�
 *  						| OOP VS AOP
 *  							=> AOP�� OOP�� ������ ����
 *  								=> CallBack => �ڵ� ȣ��
 *  								=> � �޼ҵ�����
 *  								=> ��� ��������
 *  tyr{
 *  	getConnection() ==> BEFORE
 *  	..
 *  	..
 *  	..
 *  }catch(Exception e){
 *  	e.printStackTrace()
 *  }
 *  finally{
 *  	disConnection() ==> AFTER
 *  }
 *  
 *  @ == Before
 *  tyr{
 *  	---------------- Around setAutoCommit(false)
 *  	 ...
 *  	 ...
 *  	---------------- commit() => Ʈ����� / �α�����
 *  }catch(Exception e){			 -------------- ���������� ���̺귯��
 *  	@ == After-Thorwing
 *  }
 *  finally{
 *  	@ == After
 *  }
 *  return ... @ == After-Return
 *  
 *  �⺻
 *  	=> �ڵ� �ҽ�
 *  	   -------
 *  		���� ���Ǵ� �ҽ�
 *  		�ٽ� ���Ǵ� �ҽ�
 */
public class BoardDAO {
	/*
	public void getConnection() {
		System.out.println("����Ŭ ����!!");
	}
	public void disConnection() {
		System.out.println("����Ŭ �ݱ�!!");
	}
	*/
	public void boardListData(int page) {
		//getConnection();
		System.out.println(page+"������ ��� ��� ");
		//disConnection();
	}
	/*  
	 *  public void display(){
	 *  	=> Before
	 *  	try{
	 *  		=> Around
	 *  	}catch(Exception e){
	 *  		=> After-Throwing
	 *  	}finally{
	 *  		=> After
	 *  	}
	 *  	return; => After-Return
	 *  }
	 *  DI / AOP => MVC(���̺귯��)
	 */
	public String boardDetailDate(String name) {
		//getConnection();
		System.out.println(name+"�� ���� �󼼺���");
		//disConnection();
		return name;
	}
	public void boardInsert() {
		//getConnection();
		System.out.println("�Խù� �߰� �Ϸ�");
		//disConnection();
	}
	public void boardUpdate() {
		//getConnection();
		System.out.println("�Խù� ���� �Ϸ�");
		//disConnection();
	}
	public void boardDelete() {
		//getConnection();
		System.out.println("�Խù� ���� �Ϸ�");
		//disConnection();
	}
	public void print() {
		System.out.println("���α׷� ����");
	}
}
