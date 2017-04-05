package com.tcoveney.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcoveney.dao.CustomerDao;
import com.tcoveney.model.Customer;

@RestController
@RequestMapping("/api/customer")
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
	
	@PostMapping("/store")
	public ResponseEntity<String> store(@Validated @ModelAttribute Customer customer) {
		log.info("Called CustomerApiController.store()");
		
		// TODO: Determine how to use existing validation here. Possibly same as regular controller.
		
		log.info("Customer name: " + customer.getName());
		
//		int primaryKey = customerDao.insert(customer);
//		log.info("New customer primary key: " + primaryKey);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		return new ResponseEntity<String>("New customer created", responseHeaders, HttpStatus.CREATED);
	}

}
