package com.ots.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ots.beans.UserInfo;
import com.ots.dao.TraderDAO;

@Controller
@RequestMapping("searchclnt.html")
public class SearchClientController {
	
	@Autowired
	private TraderDAO traderDAO;
	
	private static final Logger log = Logger.getLogger(SearchClientController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String loginPage(HttpServletRequest req, HttpServletResponse resp, 
			@ModelAttribute("model") ModelMap model){
		log.debug("Entering Client SearchClientController GET");
		
		model.addAttribute("Page", "searchclient");
		return "main";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String verifyUser(HttpServletRequest req, HttpServletResponse resp,
			@ModelAttribute("model") ModelMap model) {
		String fetchBy = req.getParameter("searchtype");
		String IdNameCity = req.getParameter("searchval");
		List<UserInfo> userList = traderDAO.FetchClientRecords(fetchBy, IdNameCity);
		model.addAttribute("txnlist", userList);
		model.addAttribute("Page", "searchresult");
		return "main";
	}
}
