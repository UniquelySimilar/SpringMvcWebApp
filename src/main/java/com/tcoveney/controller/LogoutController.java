package com.tcoveney.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logoutuser")
public class LogoutController {
	private static final Logger logger = LogManager.getLogger(LogoutController.class);

	@GetMapping("")
	public String logoutUser() {
		return "logout";
	}
}
