package com.tcoveney.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tcoveney.dao.CustomerDao;
import com.tcoveney.model.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	private Log log = LogFactory.getLog(CustomerController.class);
	
	@Autowired
	private CustomerDao customerDao;
	
	@GetMapping("")
    public String index(Model model) {
		//log.info("Called CustomerController.index()");
		
		List<Customer> customerList = customerDao.list();
		
		model.addAttribute(customerList);

		return "customer/index";
    }
	
	@GetMapping("/{id}")
	public ModelAndView show(@PathVariable("id") int id) {
		return null;
	}

	@GetMapping("/create")
	public String create(Model model) {
		log.info("Called CustomerController.create()");
		
		model.addAttribute(new Customer());
		
		return "customer/create";
	}
	
	@PostMapping("/store")
	public String store(@ModelAttribute Customer customer) {
		log.info("Called CustomerController.store()");
		
		log.info("Customer name: " + customer.getName());
		
		int primaryKey = customerDao.insert(customer);
		log.info("New customer primary key: " + primaryKey);
		
		return "redirect:/dispatch/customer";
	}

}
