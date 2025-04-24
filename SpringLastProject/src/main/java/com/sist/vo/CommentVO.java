package com.sist.vo;
import java.util.*;

import lombok.Data;
/*
NO         NOT NULL NUMBER       
CNO                 NUMBER       
TYPE                NUMBER       
USERID              VARCHAR2(20) 
USERNAME   NOT NULL VARCHAR2(52) 
SEX                 VARCHAR2(6)  
MSG        NOT NULL CLOB         
REGDATE             DATE         
GROUP_ID            NUMBER       
GROUP_STEP          NUMBER 
 */
@Data
public class CommentVO {
	private int no,cno,type,group_id,group_step;
	private String userid,username,sex,msg,dbday;
	private Date regdate;
}
