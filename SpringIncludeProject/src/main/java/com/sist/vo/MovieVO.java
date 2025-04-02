package com.sist.vo;

import lombok.Data;

/*
MNO         NUMBER        
TITLE       VARCHAR2(600) 
POSTER      VARCHAR2(260) 
DIRECTOR    VARCHAR2(200) 
GENRE       VARCHAR2(100) 
 */
@Data
public class MovieVO {
	private int mno;
	private String title,poster,director,genre;
}
