package com.example.springboot.mapper;

import java.util.ArrayList;
import java.util.List;

import com.example.springboot.dto.CustomerDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CustomerMapper {
	ArrayList<CustomerDTO> getAllCustomer();
    
    CustomerDTO getCustomerByName(String name);

    Integer addCustomer(CustomerDTO customer);

    Integer updateCustomerById(CustomerDTO customer);
    
    Integer updateCustomerAgeById(CustomerDTO customer);

    Integer deleteAllCustomer();
    
    Integer deleteCustomerById(Long id);
    
    int getCustomerById(Long id);
    
    CustomerDTO getCustomerByAccount(String account);

}


