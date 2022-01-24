package com.example.springboot.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springboot.dto.CustomerDTO;
import com.example.springboot.mapper.CustomerMapper;


@Controller
public class UserController {
	
	@Autowired
	protected CustomerMapper customerMapper; 	
	
	
	private Logger log = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/login")
	public String login(Model model) {
		
		return "login";
	}
	
	
	@PostMapping("/login")
	public String loginSubmit(Model model, HttpServletRequest request, HttpServletResponse response, CustomerDTO customer) {
		 
		//取session 
		HttpSession session = request.getSession();
		
		
		//取得前端的帳戶資料
		System.out.println(customer.getAccount()+customer.getPassword());
		
		
		//確認帳密是否正確, 怎麼處理null ?
		CustomerDTO customerdb = new CustomerDTO();
		 try {
			 
			customerdb = customerMapper.getCustomerByAccount(customer.getAccount());
			if(customerdb==null) {
				
				System.out.println("帳號不存在");
				return "login"; 
			}
		 }catch (NullPointerException e){
			 e.printStackTrace();
		 }
		 

		
		if(customer.getPassword().equals(customerdb.getPassword())) {
			request.getSession().setAttribute("user", customerdb.getAccount());
		}
		
		
		//找session是否存在
		Object userInfo =session.getAttribute("user");
        
        System.out.println("帳號"+userInfo);
        
        if (userInfo == null) {
            log.info("沒有登入");
            try {
				response.getWriter().write("Please Login In");
			} catch (IOException e) {				
				e.printStackTrace();
			}
            
        } else {
            //log.info("已經登入過啦，使用者資訊為：" + session.getAttribute("user"));
        	System.out.println("已經登入過啦，使用者資訊為：" +request.getSession().getAttribute("user"));
        }

		return "login";
	}

}
