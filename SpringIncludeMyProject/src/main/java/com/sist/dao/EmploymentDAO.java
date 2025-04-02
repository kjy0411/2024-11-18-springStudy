package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sist.mapper.EmploymentMapper;

@Controller
public class EmploymentDAO {
	@Autowired
	private EmploymentMapper mapper;
	
}
