package com.grocery.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JspController {

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String home() {
		for (var object : SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()) {
			String tempObject = object.toString();
			if (tempObject.equalsIgnoreCase("ROLE_ADMIN")) {
				return "redirect:admin/offers";
			}
			if (tempObject.equalsIgnoreCase("ROLE_EMPLOYEE")) {
				return "redirect:employee/questions";
			}
		}
		return "customer/home";
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = { "/resetpassword" }, method = RequestMethod.GET)
	public String resetpassword() {
		return "resetpassword";
	}
	
	@RequestMapping(value = { "/checkout" }, method = RequestMethod.GET)
	public String checkout() {
		return "customer/checkout";
	}
	
	@RequestMapping(value = { "/mypurchases" }, method = RequestMethod.GET)
	public String mypurchases() {
		return "customer/mypurchases";
	}
	
	@RequestMapping(value = { "/myquestions" }, method = RequestMethod.GET)
	public String myquestions() {
		return "customer/myquestions";
	}

	@RequestMapping(value = { "/admin/offers" }, method = RequestMethod.GET)
	public String adminOffers() {
		return "admin/offers";
	}
	
	@RequestMapping(value = { "/admin/products" }, method = RequestMethod.GET)
	public String adminProducts() {
		return "admin/products";
	}
	
	@RequestMapping(value = { "/admin/replenishment" }, method = RequestMethod.GET)
	public String adminReplenishment() {
		return "admin/replenishment";
	}
	
	@RequestMapping(value = { "/admin/customersupport" }, method = RequestMethod.GET)
	public String adminCustomerSupport() {
		return "admin/customersupport";
	}
	
	@RequestMapping(value = { "/employee/questions" }, method = RequestMethod.GET)
	public String employeeQuestions() {
		return "employee/questions";
	}

	@RequestMapping(value = { "/error" }, method = RequestMethod.GET)
	public String error() {
		return "error";
	}

}
