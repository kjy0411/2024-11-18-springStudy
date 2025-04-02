package com.sist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.*;

@Service
public class EmploymentServiceImpl {
	@Autowired
	private CompanyDAO cDao;
	@Autowired
	private EmploymentDAO eDao;
}
