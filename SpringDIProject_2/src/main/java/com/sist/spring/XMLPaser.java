package com.sist.spring;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;

import java.lang.reflect.Method;
import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/*  
 *  XML �Ľ�
 * 	= DOM 
 * 		�ӵ��� ������ => �޸� Ʈ�� ���·� ���� / CRUD
 * 	= SAX : MyBatis / Spring
 * 		�ӵ��� ������ => �±��� ��/�Ӽ����� �б�
 * 		�̺�Ʈ
 * 
 *  	<?xml version="1.0" encoding="UTF-8"?>				=> startDocument()
 *  	<beans>												=> startElement()
 *  		<bean id="sa" class="com.sist.spring.Sawon">	=> startElement()
 *  			<property name="name" value="ȫ�浿"/>		=> startElemenet() endElement()
 *  			<property name="sex" value="����"/>			=> startElemenet() endElement()
 *  			<property name="age" value="25"/>			=> startElemenet() endElement()
 *  		</bean>											=> endElement()
 *  	</beans>											=> endElement()
 *  														=> endDocument()
 */
public class XMLPaser extends DefaultHandler{
	Map clsMap=new HashMap();
	Object obj;
	Class clsName;
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		try {
			if(qName.equals("bean")) {
				String id=attributes.getValue("id");
				String cls=attributes.getValue("class");
				clsName=Class.forName(cls);
				obj=clsName.getDeclaredConstructor().newInstance();
				// Ŭ���� �̸����� ���� (��������, ��ü ����, �޼ҵ� ȣ��)
				clsMap.put(id, obj); // ���ÿ��� ����ȯ
			}
			// DI => setter DI
			if(qName.equals("property")) {
				String name=attributes.getValue("name");	
				String value=attributes.getValue("value");	
				
				// Method ȣ��
				Method[] methods=clsName.getDeclaredMethods();
				for(Method m:methods) {
					if(m.getName().equalsIgnoreCase("set"+name)) {
						if(name.equals("age")) {
							m.invoke(obj, Integer.parseInt(value));
						}else {
							m.invoke(obj,value);
							//sa.setName("ȫ�浿");
						}
					}
				}
			}
		} catch (Exception e) {}
	}
}
