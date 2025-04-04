package com.sist.vo;

import lombok.Data;
import java.util.*;
@Data
public class BoardVO {
	private int no,hit,gorup_id,group_step,group_tab,root,depth;
	private String name,subject,content,pwd,dbday;
	private Date regdate;
}
