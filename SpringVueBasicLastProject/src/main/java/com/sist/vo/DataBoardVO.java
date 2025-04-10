package com.sist.vo;

import lombok.Data;

/*
NO        NOT NULL NUMBER         
NAME      NOT NULL VARCHAR2(51)   
SUBJECT   NOT NULL VARCHAR2(2000) 
CONTENT   NOT NULL CLOB           
PWD       NOT NULL VARCHAR2(10)   
REGDATE            DATE           
HIT                NUMBER         
FILENAME           VARCHAR2(2000) 
FILESIZE           VARCHAR2(1000) 
FILECOUNT          NUMBER
REPLYCOUNT          NUMBER 
 */
import java.util.*;

import org.springframework.web.multipart.MultipartFile;
@Data
public class DataBoardVO {
	private int no,hit,filecount,replycount;
	private String name,subject,content,pwd,filename,filesize,dbday;
	private Date regdate;
	// upload
	private List<MultipartFile> files;
}
