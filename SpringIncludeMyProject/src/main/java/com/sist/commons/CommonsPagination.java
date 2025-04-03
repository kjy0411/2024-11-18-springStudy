package com.sist.commons;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
// 메모리 할당X => static 메소드
/*  
 *  공통으로 사용되는 기능 : 메소드
 *  => 자동화 처리 => AOP => 모든 메소드 적용
 *  			   --- 트랜잭션 / 보안 / footer
 *  => 원하는 위치에서 호출 => 메소드 호출
 */
public class CommonsPagination {
	/**
	 * @param page : 출력 페이지
	 * @param count : 총 개수
	 * @param rowSize : 한 페이지에 출력할 개수
	 * @param BLOCK : 페이지네이션 크기
	 */
	public static Map pageConfig(String page,int count, int rowSize, final int BLOCK) {
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int totalpage=(int)(Math.ceil(count/(double)rowSize));
		if(curpage>totalpage)
			curpage=totalpage;
		
		Map map=new HashedMap();
		int start=(rowSize*curpage)-(rowSize-1);
		int end=(rowSize*curpage);
		
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		map.put("start", start);
		map.put("end", end);
		HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		return map;
	}
}
