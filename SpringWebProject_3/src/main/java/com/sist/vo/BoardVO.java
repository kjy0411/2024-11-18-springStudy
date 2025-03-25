package com.sist.vo;
/*
NO      NOT NULL NUMBER         
NAME    NOT NULL VARCHAR2(51)   
SUBJECT NOT NULL VARCHAR2(2000) 
CONTENT NOT NULL CLOB           
PWD     NOT NULL VARCHAR2(10)   
REGDATE          DATE           
HIT              NUMBER   
 */
import java.util.*;
import lombok.Data;
/*  
 *  1. 동작 방법 / 사용법
 *  2. 데이터베이스 연동 => 화면
 *  ---------------------- 반복
 */
// 필수 => CRUD
@Data
public class BoardVO {
	private int no,hit;
	private String name,subject,content,pwd,dbday;
	private Date regdate;
}
