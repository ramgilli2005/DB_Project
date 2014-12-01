package com.ots.controller;

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

import com.ots.beans.ClientDbo;
import com.ots.dao.ClientDAO;

@Controller
@RequestMapping("clienthome.html")
public class ClientHomeController {
	
	@Autowired
	private ClientDAO clientDAO;
	
	private static final Logger log = Logger.getLogger(ClientHomeController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String loginPage(HttpServletRequest req, HttpServletResponse resp, 
			@ModelAttribute("model") ModelMap model){
		log.debug("Entering Client Home Controller GET");
		HttpSession session = req.getSession();
		String clientId = (String)session.getAttribute("clientId");
		ClientDbo clientDbo = clientDAO.ViewOilCashReserves(clientId);
		log.debug((null != clientDbo ? clientDbo.getQuantiy() : "null obj"));
		model.addAttribute("curQty", (null != clientDbo ? clientDbo.getQuantiy() : "0"));
		model.addAttribute("Page", "clienthome");
		return "main";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String verifyUser(HttpServletRequest req, HttpServletResponse resp,
			@ModelAttribute("model") ModelMap model) {
		model.addAttribute("Page", "");
		return "main";
	}
}
