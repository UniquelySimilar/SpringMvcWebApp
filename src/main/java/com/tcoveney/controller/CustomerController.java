package com.tcoveney.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	private Log log = LogFactory.getLog(CustomerController.class);
	
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
		//log.info("Called CustomerController.index()");
		
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
		//log.info("Called CustomerController.create()");
		
		Customer customer = new Customer();
		customer.setState("Colorado");	// Default for drop down
		model.addAttribute(customer);
		
		return "customer/create";
	}
	
	@PostMapping("/store")
	public String store(@Validated @ModelAttribute Customer customer, BindingResult result) {
		//log.info("Called CustomerController.store()");
		
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
		//log.info("Called CustomerController.edit() for id = " + id);
		
		Customer customer = this.customerDao.find(id);
		model.addAttribute(customer);
		
		httpSession.setAttribute("customerId", new Integer(id));
		httpSession.setAttribute("createdAt", customer.getCreatedAt());
		
		return "customer/edit";
	}
	
	@PutMapping("/update")
	public String update(@Validated @ModelAttribute Customer customer, BindingResult result, HttpSession httpSession) {
		//log.info("Called CustomerController.update()");
		//log.info(customer.toString());
		
		if (result.hasErrors()) {
			//log.warn("New Customer contains validation error");
			return "customer/edit";
		}
		
		Integer customerId = (Integer) httpSession.getAttribute("customerId");
		customer.setId(customerId.intValue());
		//log.info("Customer ID from HttpSession: " + customerId);
		Date createdAt = (Date) httpSession.getAttribute("createdAt");
		customer.setCreatedAt(createdAt);
		//log.info("Customer creation date from HttpSession: " + createdAt.toString());		
		
		customerDao.update(customer);
		
		return "redirect:/customer";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		//log.info("Called CustomerController.delete() for id = " + id);
		
		this.customerDao.delete(id);
		
		return "redirect:/customer";
	}

}
