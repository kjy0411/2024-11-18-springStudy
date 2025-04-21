package com.sist.service;

import java.util.*;
import com.sist.vo.*;

public interface BusanInfoService {
	public List<BusanInfoVO> BusanInfoListData(Map map);
	public int BusanInfoTotalPage(int cno);
	public BusanInfoVO BusanInfoDetailData(int no);
}
