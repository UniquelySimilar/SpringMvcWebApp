package com.tcoveney.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tcoveney.Utils;
import com.tcoveney.dao.CustomerDao;
import com.tcoveney.model.Customer;
import com.tcoveney.validator.CustomerValidator;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	private static final Logger logger = LogManager.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerDao customerDao;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new CustomerValidator());
	}

	@ModelAttribute
	public void addAttributes(Model model) {
		Map<String,String> stateList = Utils.getStateList();
		model.addAttribute("stateList", stateList);
	}
	
	@GetMapping("")
    public String index(Model model) {
		//logger.info("CUSTOMER INDEX");
		
		List<Customer> customerList = customerDao.findAll();
		
		model.addAttribute(customerList);

		return "customer/index";
    }
	
	@GetMapping("/{id}")
	public ModelAndView show(@PathVariable("id") int id) {
		return null;
	}

	@GetMapping("/create")
	public String create(Model model) {
		//logger.info("CUSTOMER CREATE");
		
		Customer customer = new Customer();
		customer.setState("Colorado");	// Default for drop down
		model.addAttribute(customer);
		
		return "customer/create";
	}
	
	@PostMapping("/store")
	public String store(@Validated @ModelAttribute Customer customer, BindingResult result) {
		//logger.info("CUSTOMER STORE");
		
		if (result.hasErrors()) {
			//log.warn("New Customer contains validation error");
			return "customer/create";
		}
		
		customerDao.insert(customer);
		//log.info("New customer primary key: " + primaryKey);
		
		return "redirect:/customer";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model, HttpSession httpSession) {
		//logger.info("CUSTOMER EDIT for id = " + id);
		
		Customer customer = this.customerDao.find(id);
		model.addAttribute(customer);
		
		return "customer/edit";
	}
	
	@PutMapping("/update")
	public String update(@Validated @ModelAttribute Customer customer, BindingResult result) {
		//logger.info("CUSTOMER UPDATE");
		//log.info(customer.toString());
		
		if (result.hasErrors()) {
			//log.warn("New Customer contains validation error");
			return "customer/edit";
		}
				
		customerDao.update(customer);
		
		return "redirect:/customer";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		//logger.info("CUSTOMER DELETE for id = " + id);
		
		this.customerDao.delete(id);
		
		return "redirect:/customer";
	}

}
