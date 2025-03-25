package com.sist.aop;
import java.util.*;

import org.aspectj.lang.ProceedingJoinPoint;

import com.sist.dao.*;
public class EmpAspect {
	private EmpDAO dao;

	public void setDao(EmpDAO dao) {
		this.dao = dao;
	}
	// ���� �� ó��
	public void before() {
		dao.getConnection();
		System.out.println("EmpDAO:getConnection() Call...");
	}
	// finally ó��
	public void after() {
		dao.disConnection();
		System.out.println("EmpDAO:disConnection() Call...");
	}
	// ���� �ٽ� �ڵ� ó��
	public Object around(ProceedingJoinPoint jp) throws Throwable{
		// �α� ����
		Object obj=null;
		long start=System.currentTimeMillis();
		// ����ڰ� ��û�� �޼ҵ��� ���� �б� => ��
		// => Ʈ����� => setAitoCommit(false)/ �ΰ�
		System.out.println("ȣ��� �޼ҵ�:"+jp.getSignature().getName());
		// �޼ҵ� ȣ��
		obj=jp.proceed();// ������ �޼ҵ� ȣ�� => empListData//empDetailData
		long end=System.currentTimeMillis();
		System.out.println("����ð�:"+(end-start));
		// commit() ����
		return obj;
	}
	// catch ����� ó��
	public void afterThrowing(Throwable ex) {
		System.out.println("======= ���� �߻� =======");
		ex.printStackTrace();
		// ���� ���� ó��
		// => #ControllerAdvice
	}
	// ���� ����ÿ� ó��
	public void afterReturning(Object obj) {
		System.out.println("====== ����� �ڵ� ó�� =====");
		// => ���������� �ʿ��� request�� ���� => Ǫ��
		List<EmpVO> list=(List<EmpVO>)obj;
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "
						+vo.getEname()+" "
						+vo.getJob()+" "
						+vo.getDbday()+" "
						+vo.getSal());
		}
	}
}
