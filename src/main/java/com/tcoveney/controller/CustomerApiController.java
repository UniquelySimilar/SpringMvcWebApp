package com.tcoveney.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcoveney.dao.CustomerDao;
import com.tcoveney.model.Customer;

@RestController
@RequestMapping("/customer/api")
public class CustomerApiController {
	private Log log = LogFactory.getLog(CustomerController.class);
	
	@Autowired
	private CustomerDao customerDao;
	
	@GetMapping("")
    public List<Customer> index(Model model) {
		log.info("Called CustomerApiController.index()");
		
		List<Customer> customerList = customerDao.list();

		return customerList;
    }

}
