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

import com.ots.beans.Txn;
import com.ots.dao.ClientDAO;

@Controller
@RequestMapping("placeorder.html")
public class PlaceOrderController {
	
	@Autowired
	ClientDAO clientDAO;
	
	private static final Logger log = Logger.getLogger(PlaceOrderController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String previewPage(HttpServletRequest req, HttpServletResponse resp, 
			@ModelAttribute("model") ModelMap model){
		log.debug("Entering PlaceOrder Controller GET");
		return null;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String previewTrade(HttpServletRequest req, HttpServletResponse resp,
			@ModelAttribute("model") ModelMap model) {
		//Place the order
		HttpSession session = req.getSession();
		//String clientId = (String)session.getAttribute("clientId");
		String clientId = req.getParameter("clientId"); 
		Txn txn = new Txn();
		txn.setClientId(clientId);
		double qty = Double.parseDouble(req.getParameter("trnqty"));
		String orderType = req.getParameter("trntype");
		String commsnType = req.getParameter("commsntype");
		double commission = Double.parseDouble(req.getParameter("commsnamt"));
		double txnCost = Double.parseDouble(req.getParameter("txncost"));
		
		txn.setQuantity(qty);
		txn.setType(orderType);
		txn.setComsnType(commsnType);
		txn.setTotalComsn(commission);
		txn.setTxnCost(txnCost);
		
		//Place the order
		int txnId = clientDAO.makeTxn(txn);
		if(txnId != -1){
			log.debug("Transaction Success!!!");
			model.addAttribute("txnId", txnId);
		} else {
			log.error("Txn failure!!!");
			model.addAttribute("trntype", orderType);
			model.addAttribute("trnQty", qty);
			model.addAttribute("commsntype", commsnType);
			model.addAttribute("commsnamt", commission);
			model.addAttribute("txnCost", txnCost);
			model.addAttribute("clientId", clientId);
			model.addAttribute("errorMsg", "Unable to place trade!!!");
			model.addAttribute("Page", "transact_preview");
			return "main";
		}
		model.addAttribute("trntype", orderType);
		model.addAttribute("trnQty", qty);
		model.addAttribute("commsntype", commsnType);
		model.addAttribute("commsnamt", commission);
		model.addAttribute("txnCost", txnCost);
		model.addAttribute("Page", "transact_success");
		return "main";
	}
}
