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

import com.ots.beans.ClientDbo;
import com.ots.beans.TxnLog;
import com.ots.dao.ClientDAO;
import com.ots.dao.TraderDAO;

@Controller
@RequestMapping("Mgrtxnhist.html")
public class MgrHistoryController {
	
	@Autowired
	private TraderDAO traderDAO;
	
	private static final Logger log = Logger.getLogger(MgrHistoryController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String loginPage(HttpServletRequest req, HttpServletResponse resp, 
			@ModelAttribute("model") ModelMap model){
		log.debug("Entering Mgr Home Controller GET");
		List<TxnLog> txnLogList = traderDAO.ViewTxns();
		log.debug("List size: " + txnLogList.size());
		model.addAttribute("txnlist", txnLogList);
		
		model.addAttribute("Page", "Mgrtxnhist");
		return "main";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String verifyUser(HttpServletRequest req, HttpServletResponse resp,
			@ModelAttribute("model") ModelMap model) {
		model.addAttribute("Page", "");
		return "main";
	}
}
