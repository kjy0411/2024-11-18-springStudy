package com.sist.auto;

import org.springframework.stereotype.Component;

@Component
public class Oracle implements DAO{

	@Override
	public void connection() {
		// TODO Auto-generated method stub
		System.out.println("오라클 연결");
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		System.out.println("오라클 해제");
	}
	
}
