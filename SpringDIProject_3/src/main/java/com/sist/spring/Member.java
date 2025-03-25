package com.sist.spring;

import lombok.Data;
/*  
 *  xml
 *  	application-datasource.xml : DB
 *  	application-context.xml : ����� ���� Ŭ���� / MVC
 *  	application-security.xml : ����
 */
@Data
public class Member {
	private int mno;
	private String name;
	private String address;
}
