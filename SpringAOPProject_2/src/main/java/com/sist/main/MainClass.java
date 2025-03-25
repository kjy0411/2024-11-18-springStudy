package com.sist.main;
/*  
 *  Proxy => �븮�� => AOP
 *  
 *  ���α׷� => �ٽ� �ڵ�
 *  		  -------
 *  		  �ΰ����� �ڵ� => ��Ƽ� ���� => ������ / AOP
 *  			| �����ͺ��̽� ����/���� 
 *  			| ���� (�ΰ� => ����)
 *  			| Ʈ����� => commit / rollback
 *  			| �α�
 *  			| ���� �����
 *  			------------------------ AOP ���̺귯��
 *  			| ����� ���� AOP(�ѵι�)
 *  			  ------------- ���ͼ��� (�ڵ� �α���), AOP
 *  �ߺ� ���� => java:�޼ҵ�ȭ ó��
 *  ------------------------- CallBack
 *  AOP�� ����
 *  1. Aspect : �������� ����Ǵ� ����� ��Ƶ� ��� => ���� ���
 *  2. Target : �����ϴ� �� => ����ִ� ���� (����)
 *  3. Advice : ��� � ����� �߰��� ��
 *  			JoinPoiunt + PointCut
 *  4. JoinPoint : ���� => ��� ȣ��
 *  				Before
 *  				After
 *  				After-Returning
 *  				After-Throwing
 *  				Around
 *  				
 *  				public void display()
 *  				{
 *  					=> try ������ => Before
 *  					try
 *  					{
 *  						------ Around start
 *  							=> �α�
 *  							=> � �޼ҵ带 ��û
 *  							=> setAutoCommit(false)
 *  						�ٽ��ڵ� => �޼ҵ� ȣ��
 *  						------ Around end
 *  							=> �ɸ� �ð� Ȯ��
 *  							=> commit
 *  					}
 *  					cathc(Exception ex)
 *  					{
 *  						=> ���� �߻��� ó�� => After-Throwing
 *  					}
 *  					finally
 *  					{
 *  						=> ������ ���� => After
 *  					}
 *  					return => ���� ����� => After-Returning
 *  				}
 *  5. PointCut : � �޼ҵ� => execution("������ ��Ű��.Ŭ������.�޼ҵ��(..)")
 *  															---- �Ű������� 0�� �̻�
 *  
 *  	=> �޼ҵ忡 ���� �����ϴ� �ҽ��� ��Ƶд� : Aspect
 *  	=> �޼ҵ尡 ȣ��� ��				==> PointCut
 *  	=> �޼ҵ� � ��ġ�� ���������� ����	==> JoinPoint
 *  					 ------ Weaving
 *  	--------------------------------------------- Advice
 *  	=> include�� ���� : ����ø��� �ڵ�ȣ��
 *  	   ------- ���� ����
 */
public class MainClass {

	public static void main(String[] args) {
		My m=new My();
		m.execute();
		
		MyTarget mt=new MyTarget(m);
		mt.execute();
	}
	
}
