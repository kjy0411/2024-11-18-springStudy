package com.sist.web;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
@RestController
public class SeoulRestController {
	@Autowired
	private SeoulDAO sDao;
	
	@GetMapping("seoul/location_vue.do")
	public ResponseEntity<Map> seoul_location_vue(int page){
		// 실제 데이터 + 상태(정상 / 에러) 한번에 전송
		Map map=new HashMap();
		try {
			int rowSize=12;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			
			map.put("start", start);
			map.put("end", end);
			map.put("table_name", "seoul_location");
			
			List<SeoulVO> list=sDao.seoulListData(map);
			int totalpage=sDao.seoulTotalPage(map);
			
			final int BLOCK=10;
			int startPage=((page-1)/BLOCK*BLOCK)+1;
			int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
			if(endPage>totalpage) endPage=totalpage;
			
			// Vue로 전송
			map=new HashMap();
			map.put("list", list);
			map.put("curpage", page);
			map.put("totalpage", totalpage);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@GetMapping("seoul/location_detail_vue.do")
	public ResponseEntity<Map> seoul_location_detail_vue(int no){
		Map map=new HashMap();
		try {
			map.put("no", no);
			map.put("table_name", "seoul_location");
			
			SeoulVO vo=sDao.seoulDetailData(map);
			
			map=new HashMap();
			
			String address=vo.getAddress();
			int index=address.indexOf("서울");
			if(index>=0) {
				String addr1=address.substring(index).trim();
				String addr2=addr1.substring(addr1.indexOf(" ")).trim();
				String addr3=addr2.substring(0,addr2.indexOf(" ")).trim();
				
				List<FoodVO> list=sDao.foodRecommandData(addr3);
				
				map.put("address", addr1);
				map.put("list", list);
				map.put("count", list.size());
			}else {
				map.put("count", 0);
			}
			
			map.put("vo", vo);
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@GetMapping("seoul/shop_vue.do")
	public ResponseEntity<Map> seoul_shop_vue(int page){
		// 실제 데이터 + 상태(정상 / 에러) 한번에 전송
		Map map=new HashMap();
		try {
			int rowSize=12;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			
			map.put("start", start);
			map.put("end", end);
			map.put("table_name", "seoul_shop");
			
			List<SeoulVO> list=sDao.seoulListData(map);
			int totalpage=sDao.seoulTotalPage(map);
			
			final int BLOCK=10;
			int startPage=((page-1)/BLOCK*BLOCK)+1;
			int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
			if(endPage>totalpage) endPage=totalpage;
			
			// Vue로 전송
			map=new HashMap();
			map.put("list", list);
			map.put("curpage", page);
			map.put("totalpage", totalpage);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@GetMapping("seoul/shop_detail_vue.do")
	public ResponseEntity<Map> seoul_shop_detail_vue(int no){
		Map map=new HashMap();
		try {
			map.put("no", no);
			map.put("table_name", "seoul_shop");
			
			SeoulVO vo=sDao.seoulDetailData(map);
			
			map=new HashMap();
			
			String address=vo.getAddress();
			int index=address.indexOf("서울");
			if(index>=0) {
				String addr1=address.substring(index).trim();
				String addr2=addr1.substring(addr1.indexOf(" ")).trim();
				String addr3=addr2.substring(0,addr2.indexOf(" ")).trim();
				
				List<FoodVO> list=sDao.foodRecommandData(addr3);
				
				map.put("address", addr1);
				map.put("list", list);
				map.put("count", list.size());
			}else {
				map.put("count", 0);
			}
			
			map.put("vo", vo);
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}

}
