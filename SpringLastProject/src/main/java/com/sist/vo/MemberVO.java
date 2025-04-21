package com.sist.vo;

import lombok.Data;
import java.util.*;
/*
USERID     NOT NULL VARCHAR2(20)  
USERNAME   NOT NULL VARCHAR2(52)  
USERPWD    NOT NULL VARCHAR2(100) 
ENABLE              NUMBER(1)     
SEX                 CHAR(6)       
BIRTHDAY   NOT NULL VARCHAR2(20)  
EMAIL               VARCHAR2(100) 
POST       NOT NULL VARCHAR2(10)  
ADDR1      NOT NULL VARCHAR2(500) 
ADDR2               VARCHAR2(100) 
PHONE               VARCHAR2(20)  
CONTENT             CLOB          
REGDATE             DATE          
MODIFYDATE          DATE          
LASTLOGIN           DATE          

 */
// spring security
// => userid : username
// => pwd, userpwd : password
// role => ROLE_ADMIN / ROLE_USER / ROLE_MANAGER...
@Data
public class MemberVO {
	private int enable; // 활성화(1), 휴면계정(0)
	private String userid,username,userpwd,sex,birthday,email,post,addr1,addr2,phone,content,msg,authority;
	private Date regdate,modifydate,lastlogin;
}
