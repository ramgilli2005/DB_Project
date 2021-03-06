package com.ots.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("pymntapproval.html")
public class PaymentApprovalController {

	private static final Logger log = Logger.getLogger(PaymentApprovalController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String loginPage(HttpServletRequest req, HttpServletResponse resp, 
			@ModelAttribute("model") ModelMap model){
		log.debug("Entering Txn Payment Controller GET");
		model.addAttribute("pymntlist", null);
		model.addAttribute("Page", "pymntapproval");
		return "main";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String verifyUser(HttpServletRequest req, HttpServletResponse resp,
			@ModelAttribute("model") ModelMap model) {
		log.debug("Entering Txn Payment Controller POST");
		model.addAttribute("pymntlist", null);
		model.addAttribute("Page", "pymntapproval");
		return "main";
	}
}
