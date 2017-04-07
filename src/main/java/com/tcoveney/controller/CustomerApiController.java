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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcoveney.dao.CustomerDao;
import com.tcoveney.model.Customer;
import com.tcoveney.validator.CustomerValidator;

@RestController
@RequestMapping("/api/customer")
public class CustomerApiController {
	private Log log = LogFactory.getLog(CustomerController.class);
	
	@Autowired
	private CustomerDao customerDao;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new CustomerValidator());
	}
	
	@GetMapping("")
    public List<Customer> index(Model model) {
		log.info("Called CustomerApiController.index()");
		
		List<Customer> customerList = customerDao.findAll();

		return customerList;
    }
	
	@PostMapping("/store")
	public ResponseEntity<String> store(@Validated Customer customer, BindingResult result) {
		// NOTE: When the request Content-Type header is set to 'application/x-www-form-urlencoded', annotating
		// the Customer parameter with @RequestBody was resulting in an HTTP status code 415 - Unsupported Media Type
		log.info("Called CustomerApiController.store()");

		HttpHeaders responseHeaders = new HttpHeaders();
		
		if (result.hasErrors()) {
			log.warn("New Customer contains validation error");
			// TODO: Determine how to get specific validation error message
			//log.warn(result.getFieldError().toString());
			return new ResponseEntity<String>("ERROR validating new Customer", responseHeaders, HttpStatus.BAD_REQUEST);
		}
		
		log.info("Customer: " + customer.toString());
		
		int primaryKey = customerDao.insert(customer);
		log.info("New customer primary key: " + primaryKey);
		
		return new ResponseEntity<String>("New customer created", responseHeaders, HttpStatus.CREATED);
	}

}
