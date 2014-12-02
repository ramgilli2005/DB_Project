package com.ots.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ots.beans.Txn;
import com.ots.dao.TraderDAO;

@Controller
@RequestMapping("tamhome.html")
public class TraderHomeController {
	
	@Autowired
	private TraderDAO traderDAO;
	
	private static final Logger log = Logger.getLogger(TraderHomeController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String loginPage(HttpServletRequest req, HttpServletResponse resp, 
			@ModelAttribute("model") ModelMap model){
		log.debug("Entering Client Home Controller GET");
		HttpSession session = req.getSession();
		try{
			List<Txn> txnList = traderDAO.ViewPaidPendingTxn();
			model.addAttribute("txnlist", txnList);
			model.addAttribute("Page", "tamhome");
		} catch(Exception e){
			log.error("Error in fetching txns");
			model.addAttribute("errorMsg", "Something went wrong");
		}
		
		return "main";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String verifyUser(HttpServletRequest req, HttpServletResponse resp,
			@ModelAttribute("model") ModelMap model) {
		model.addAttribute("Page", "");
		return "main";
	}
}
