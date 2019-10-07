package com.tcoveney.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	@Qualifier("customerDaoHibernate")
	private CustomerDao customerDao;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new CustomerValidator());
	}
	
	@GetMapping("")
    public List<Customer> index(Model model) {
		//log.info("Called CustomerApiController.index()");
		
		List<Customer> customerList = customerDao.findAll();

		return customerList;
    }
	
	@GetMapping("/{id}")
    public Customer show(@PathVariable("id") int id) {
		//log.info("Called CustomerApiController.show()");
		
		Customer customer = customerDao.find(id);

		return customer;
    }
	
	@PostMapping("/store")
	public ResponseEntity<String> store(@Validated Customer customer, BindingResult result) {
		// NOTE: When the request Content-Type header is set to 'application/x-www-form-urlencoded', annotating
		// the Customer parameter with @RequestBody was resulting in an HTTP status code 415 - Unsupported Media Type
		//log.info("Called CustomerApiController.store()");
		//log.info("Customer: " + customer.toString());
		HttpHeaders responseHeaders = new HttpHeaders();
		
		if (result.hasErrors()) {
			log.warn("New Customer contains validation error");
			// TODO: Determine how to get specific validation error message
			//log.warn(result.getFieldError().toString());
			return new ResponseEntity<String>("ERROR validating new Customer", responseHeaders, HttpStatus.BAD_REQUEST);
		}
		
		
		int primaryKey = customerDao.insert(customer);
		//log.debug("New customer primary key: " + primaryKey);
		
		return new ResponseEntity<String>("New customer created", responseHeaders, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> update(@Validated Customer customer, BindingResult result) {
		//log.info("Called CustomerApiController.update()");
		//log.info("Customer: " + customer.toString());
		
		if (result.hasErrors()) {
			log.warn("Updated Customer contains validation error");
			// TODO: Determine how to get specific validation error message
			//log.warn(result.getFieldError().toString());
			return new ResponseEntity<String>("ERROR validating Customer update", HttpStatus.BAD_REQUEST);
		}
		
		customerDao.update(customer);
		
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String>  delete(@PathVariable("id") int id) {
		log.debug("Called CustomerApiController.delete()");
		
		customerDao.delete(id);
		
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

}
