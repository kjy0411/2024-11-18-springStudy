package com.sist.commons;
import java.util.*;

import org.apache.commons.collections.map.HashedMap;
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
	 * @param totalpage : 전체 페이지
	 * @param rowSize : 한 페이지에 출력할 개수
	 * @param BLOCK : 페이지네이션 크기
	 */
	public static Map pageConfig(String page,int totalpage, int rowSize, final int BLOCK) {
		if(page==null)
			page="1";
		page=page.replaceAll("[^0-9]", "");
		if(page.equals(""))
			page="1";
		
		int curpage=Integer.parseInt(page);
		if(curpage<1) {
			curpage=1;
		}else if(curpage>totalpage) {
			curpage=totalpage;
		}
		
		Map map=new HashedMap();
		int start=(rowSize*curpage)-(rowSize-1);
		int end=(rowSize*curpage);
		
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		map.put("start", start);
		map.put("end", end);
		map.put("curpage", curpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		return map;
	}
}
