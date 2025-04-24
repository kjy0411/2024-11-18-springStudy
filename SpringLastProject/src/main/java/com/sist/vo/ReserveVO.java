package com.sist.vo;

import lombok.Data;
import java.util.*;
/*
RNO       NOT NULL NUMBER       
FNO                NUMBER       
USERID             VARCHAR2(20) 
RDAY      NOT NULL VARCHAR2(30) 
RTIME     NOT NULL VARCHAR2(30) 
RINWON    NOT NULL VARCHAR2(20) 
REGDATE            DATE         
ISRESERVE          NUMBER 
 */
@Data
public class ReserveVO {
	private int rno,isreserve,fno;
	private String userid,rday,rtime,rinwon,dbday;
	private Date regdate;
	private FoodVO fvo=new FoodVO();
	private MemberVO mvo=new MemberVO();
}
