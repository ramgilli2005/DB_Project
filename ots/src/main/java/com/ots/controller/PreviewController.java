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

import com.ots.dao.ClientDAO;
import com.ots.util.TxnUtil;

@Controller
@RequestMapping("preview.html")
public class PreviewController {

	@Autowired
	ClientDAO clientDAO;
	
	@Autowired
	TxnUtil util;
	
	private static final String COMMISION_TYPE_CASH = "CASH";
	private static final String COMMISION_TYPE_OIL = "OIL";
	
	private static final Logger log = Logger.getLogger(PreviewController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String loginPage(HttpServletRequest req, HttpServletResponse resp, 
			@ModelAttribute("model") ModelMap model){
		log.debug("Entering Preview Controller GET");
		return null;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String verifyUser(HttpServletRequest req, HttpServletResponse resp,
			@ModelAttribute("model") ModelMap model) {
		//Client Txn preview
		HttpSession session = req.getSession();
		double oilPrice = clientDAO.getOilPrice();
		double qty = Double.parseDouble(req.getParameter("qty"));
		String clientId = (String)session.getAttribute("clientId");
		log.debug("Client Id in Session: "+clientId);
		String usrLvl = (String)session.getAttribute("usrLvl");
		String orderType = req.getParameter("ordertype");
		String commsnType = req.getParameter("commsntype");
		double commission = 0.0;
		double txnCost = 0.0;
		if(commsnType.equals(COMMISION_TYPE_CASH)){
			 commission = util.computeCashCommission(oilPrice, qty, usrLvl);
			 txnCost = util.computeCashTxnCost(commission, oilPrice, qty);
		} else if(commsnType.equals(COMMISION_TYPE_OIL)){
			commission = util.computeOilCommission(oilPrice, qty, usrLvl);
			txnCost = util.computeOilTxnCost(oilPrice, qty);
		}
		
		model.addAttribute("trntype", orderType);
		model.addAttribute("trnQty", qty);
		model.addAttribute("commsntype", commsnType);
		model.addAttribute("commsnamt", commission);
		model.addAttribute("txnCost", txnCost);
		model.addAttribute("clientId", clientId);
		
		model.addAttribute("Page", "transact_preview");
		
		return "main";
	}
}
