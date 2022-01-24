package com.example.springboot.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.springboot.mapper.CustomerMapper;

public abstract class CustomerService implements InitializingBean {
	
	@Autowired
	protected CustomerMapper customerMapper ;

}
