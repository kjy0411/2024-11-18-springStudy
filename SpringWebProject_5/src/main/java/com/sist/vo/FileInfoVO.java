package com.sist.vo;

import lombok.Data;

/*
NO          NUMBER        
FILENAME    VARCHAR2(260) 
FILESIZE    NUMBER        

 */
@Data
public class FileInfoVO {
	private int no;
	private String filename;
	private long filesize;
}
