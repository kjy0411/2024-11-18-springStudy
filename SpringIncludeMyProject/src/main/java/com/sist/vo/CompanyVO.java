package com.sist.vo;

import lombok.Data;
import java.util.*;
@Data
public class CompanyVO {
	private int ecount,take,cno,fo_count,jo,uk,man;
	private String cid,name,introduction,c_type,industry,
				representative,homepage,bu_details,address,history,
				access_key,poster,sample,dbestdate;
	private Date estdate;
}
