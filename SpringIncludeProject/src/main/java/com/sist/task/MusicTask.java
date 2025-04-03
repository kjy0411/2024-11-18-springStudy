package com.sist.task;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.sist.vo.*;
import com.sist.dao.*;
// 실시간 스케쥴러 => 크롤링 => 타켓형 광고
@Component
public class MusicTask {
	@Autowired
	private MovieDAO mDao;
	
	//@Scheduled(fixedRate = 60*1000)
	public void movieSchedule() {
		try {
			mDao.movieDelete();
			Document doc=Jsoup.connect("https://www.kobis.or.kr/kobis/business/main/searchMainDailyBoxOffice.do").get();
			String data=doc.toString();
			data=data.substring(data.indexOf("[{"),data.indexOf("}]")+2);
			//System.out.println(data);
			JSONParser jp=new JSONParser();
			JSONArray root=(JSONArray)jp.parse(data);
			for(int i=0; i<root.size();i++) {
				JSONObject obj=(JSONObject)root.get(i);
				MovieVO vo=new MovieVO();
				vo.setMno(i+1);
				vo.setTitle((String)obj.get("movieNm"));
				vo.setPoster("https://www.kobis.or.kr"+(String)obj.get("thumbUrl"));
				vo.setDirector((String)obj.get("director"));
				vo.setGenre((String)obj.get("genre"));
				System.out.println("번호:"+vo.getMno());
				System.out.println("제목:"+vo.getTitle());
				System.out.println("포스터:"+vo.getPoster());
				System.out.println("감독:"+vo.getDirector());
				System.out.println("장르:"+vo.getGenre());
				mDao.movieInsert(vo);
			}
		} catch (Exception e) {}
	}
}
