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
@RequestMapping("txnapproval.html")
public class TxnApprovalController {

	private static final Logger log = Logger.getLogger(TxnApprovalController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String loginPage(HttpServletRequest req, HttpServletResponse resp, 
			@ModelAttribute("model") ModelMap model){
		log.debug("Entering Txn Payment Controller GET");
		model.addAttribute("trnlist", null);
		model.addAttribute("Page", "tamhome");
		return "main";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String verifyUser(HttpServletRequest req, HttpServletResponse resp,
			@ModelAttribute("model") ModelMap model) {
		log.debug("Entering Txn Payment Controller POST");
		model.addAttribute("trnlist", null);
		model.addAttribute("Page", "tamhome");
		return "main";
	}
}
