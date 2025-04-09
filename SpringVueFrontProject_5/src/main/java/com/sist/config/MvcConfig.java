package com.sist.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
// XML대신 사용
/*  
 *  <aop:aspectj-autoproxy/> -- 1
 *  <mvc:annotation-driven/> -- 2
 *  <context:component-scan base-package="com.sist.*"/> -- 3
 *  <bean id="viewReolver"
 *      class="org.springframework.web.servlet.view.InternalResourceViewResolver"
 *      p:prefix="/"
 *      p:suffix=".jsp" 
 *  /> -- 4
 */
@Configuration // 자동 메모리 할당
@EnableAspectJAutoProxy // 1
@ComponentScan(basePackages = {"com.sist.*"}) // 3
@EnableWebMvc // 2
@EnableTransactionManagement // 6
@MapperScan(basePackages = {"com.sist.mapper"}) // 8
// xml / 자바 / xml+자바
public class MvcConfig implements WebMvcConfigurer{

	// HandlerMapping, HandlerAdapter 초기화
	/*  													 | @GetMapping(URI), @PostMapping(URI)
	 *  요청 (*.do) === DispatcherServlet === HandlerMapping === Model(@Controller,@RestController) <=> DAO
	 *  														  | request/model
	 *  														DispatcherServlet
	 *  														  | return값으로 JSP를 찾아주는 역할
	 *  														ViewResolver
	 *  														  | request
	 *  														JSP
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	// 4
	@Bean("viewResolver")
	public ViewResolver viewResolver() {
		InternalResourceViewResolver ir=new InternalResourceViewResolver();
		ir.setPrefix("/");
		ir.setSuffix(".jsp");
		return ir;
	}
	/*
	 *      <bean  id="ds" class="org.apache.commons.dbcp.BasicDataSource"
	 *         p:driverClassName="#{db['driver']}"
	 *         p:url="#{db['url']}"
	 *         p:username="#{db['username']}"
	 *         p:password="#{db['password']}"
	 *      /> -- 5
	 *      <tx:annotation-driven/> -- 6
	 *      <bean 
	 *         id="ssf"
	 *         class="org.mybatis.spring.SqlSessionFactoryBean"
	 *         p:dataSource-ref="ds"
	 *      /> -- 7
	 *      <mybatis-spring:scan base-package="com.sist.mapper"
	 *        factory-ref="ssf"
	 *      /> -- 8
     */
	// 5
	@Bean("ds")
	public DataSource dataSource() {
		BasicDataSource ds=new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		ds.setUsername("hr");
		ds.setPassword("happy");
		return ds;
	}
	// 7
	@Bean("ssf")
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean ssf=new SqlSessionFactoryBean();
		ssf.setDataSource(dataSource());
		return ssf.getObject();
	}
	// 6
	@Bean("transactionManager")
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tx=new DataSourceTransactionManager();
		tx.setDataSource(dataSource());
		return tx;
	}
}
