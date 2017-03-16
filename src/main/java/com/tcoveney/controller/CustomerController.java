package com.tcoveney.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tcoveney.dao.CustomerDao;
import com.tcoveney.model.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	private Log log = LogFactory.getLog(CustomerController.class);
	
	@Autowired
	private CustomerDao customerDao;
	
	@GetMapping("/")
    public String index() {
		log.info("Called CustomerController.index()");
		
		List<Customer> customerList = customerDao.list();

		return "customer/index";
    }

}
