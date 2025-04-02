package com.sist.vo;

import lombok.Data;
import java.util.*;
@Data
public class EmploymentVO {
	private int eno,salary,education,hit,fo_count,se_count,rtype,dtype,num;
	private String name,title,personal_history,salary_str,loc,emp_type,cid,content,dbregdate,dbdeadline;
	private Date regdate,deadline;
}
