package com.tcoveney.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ModelAndView index() {
		//log.info("Called CustomerController.index()");
		
		List<Customer> customerList = customerDao.list();

		ModelAndView modelAndView = new ModelAndView("customer/index");
		modelAndView.addObject(customerList);

        return modelAndView;
    }
	
	@GetMapping("/{id}")
	public ModelAndView show(@PathVariable("id") int id) {
		return null;
	}

	@GetMapping("/create")
	public ModelAndView create() {
		log.info("Called CustomerController.create()");
		
		Customer customer = new Customer();
		ModelAndView modelAndView = new ModelAndView("customer/create");
		modelAndView.addObject(customer);
		
		return modelAndView;
	}
	
	@PostMapping("/store")
	public String store() {
		log.debug("Called CustomerController.store()");
		
		return "redirect:/dispatch/customer";
	}

}
