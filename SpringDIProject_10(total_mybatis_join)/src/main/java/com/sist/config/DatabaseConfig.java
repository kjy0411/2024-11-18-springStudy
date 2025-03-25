package com.sist.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xmlns:mybatis-spring="http://mybatis.org/schema/mybatis-spring"
		xmlns:p="http://www.springframework.org/schema/p"
		xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
			http://mybatis.org/schema/mybatis-spring http://mybatis.org/schema/mybatis-spring-1.2.xsd">
	    <!-- DataSource���� : �����ͺ��̽��� ������ �����ϴ� ���� -->
	    <bean id="ds" class="org.apache.commons.dbcp.BasicDataSource"
	       p:driverClassName="oracle.jdbc.driver.OracleDriver"
	       p:url="jdbc:oracle:thin:@localhost:1521:XE"
	       p:username="hr"
	       p:password="happy"
	    />
	    <!-- MyBatis���� : SqlSessionFactory : ����Ŭ ����/���� -->
	    <bean id="ssf" class="org.mybatis.spring.SqlSessionFactoryBean"
	      p:dataSource-ref="ds"
	    />
	    <!-- Mapper���� MapperFactoryBean : SQL������ �о ���� / ����� -->
	    <mybatis-spring:scan base-package="com.sist.mapper" factory-ref="ssf"/>
	</beans>
 */
//application-datasource.xml�� ��ü
@Configuration
@MapperScan(basePackages = "com.sist.mapper")
//<mybatis-spring:scan base-package="com.sist.mapper" factory-ref="ssf"/>
public class DatabaseConfig {
	/*
		<bean id="ds" class="org.apache.commons.dbcp.BasicDataSource"
	       p:driverClassName="oracle.jdbc.driver.OracleDriver"
	       p:url="jdbc:oracle:thin:@localhost:1521:XE"
	       p:username="hr"
	       p:password="happy"
	    />
	 */
	@Bean("ds")
	public DataSource dataSource() {
		BasicDataSource ds=new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		ds.setUsername("hr");
		ds.setPassword("happy");
		return ds;
	}
	/*
		<bean id="ssf" class="org.mybatis.spring.SqlSessionFactoryBean"
	      p:dataSource-ref="ds"
	    />
	 */
	@Bean("ssf")
	public SqlSessionFactory sqlSessionFactory() throws Throwable {
		SqlSessionFactoryBean ssf=new SqlSessionFactoryBean();
		ssf.setDataSource(dataSource());
		return ssf.getObject();
	}
}
