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
@Component
public class MusicTask {
	//@Scheduled(fixedRate = 60*1000)
	public void movieSchedule() {
		try {
			Document doc=Jsoup.connect("https://www.kobis.or.kr/kobis/business/main/searchMainDailyBoxOffice.do").get();
		} catch (Exception e) {}
	}
}
