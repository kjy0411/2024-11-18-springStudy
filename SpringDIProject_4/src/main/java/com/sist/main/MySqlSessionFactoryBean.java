package com.sist.main;

import org.mybatis.spring.SqlSessionFactoryBean;

public class MySqlSessionFactoryBean extends SqlSessionFactoryBean{
	public MySqlSessionFactoryBean() {
		setDataSource(new MyBasicDataSource());
	}
}
