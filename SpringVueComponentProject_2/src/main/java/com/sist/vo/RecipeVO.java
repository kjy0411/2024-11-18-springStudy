package com.sist.vo;
/*
NO         NOT NULL NUMBER         
TITLE      NOT NULL VARCHAR2(4000) 
POSTER     NOT NULL VARCHAR2(260)  
CHEF       NOT NULL VARCHAR2(500)  
LINK                VARCHAR2(300)  
HIT                 NUMBER         
 */
import lombok.Data;

@Data
public class RecipeVO {
	private int no,hit;
	private String title,poster,chef;
}
