package com.sist.spring;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ApplicationContext {
	private Map map=new HashMap();
	public ApplicationContext(String path) {
		try {
			SAXParserFactory spf=SAXParserFactory.newInstance();
			SAXParser sp=spf.newSAXParser();
			XMLPaser xp=new XMLPaser();
			sp.parse(new File(path), xp);
			map=xp.clsMap;
		} catch (Exception e) {}
	}
	public Object getBean(String key) {
		return map.get(key);
	}
}
