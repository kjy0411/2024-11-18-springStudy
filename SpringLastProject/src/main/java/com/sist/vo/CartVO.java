package com.sist.vo;

import lombok.Data;
import java.util.*;
/*
CNO     NOT NULL NUMBER       
GNO              NUMBER       
USERID           VARCHAR2(20) 
ACCOUNT          NUMBER       
ISBUY            NUMBER       
REGDATE          DATE       
 */
@Data
public class CartVO {
	private int cno,gno,account,isbuy;
	private String userid,dbday;
	private Date regdate;
	private GoodsVO gvo=new GoodsVO();
}
