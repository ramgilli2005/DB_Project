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
import com.ots.beans.Txn;
import com.ots.dao.ClientDAO;

@Controller
@RequestMapping("payment.html")
public class PaymentController {
	@Autowired
	ClientDAO clientDAO;
	
	private static final Logger log = Logger.getLogger(PaymentController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String loginPage(HttpServletRequest req, HttpServletResponse resp, 
			@ModelAttribute("model") ModelMap model){
		log.debug("Entering Payment Controller GET");
		model.addAttribute("Page", "payment");
		return "main";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String verifyUser(HttpServletRequest req, HttpServletResponse resp,
			@ModelAttribute("model") ModelMap model) {
		log.debug("Entering payment Controller POST");
		HttpSession session = req.getSession();
		String clientId = (String)session.getAttribute("clientId");
		ClientDbo clientDbo = clientDAO.ViewOilCashReserves(clientId);
		int txnId = Integer.parseInt(req.getParameter("transactionId"));
		Txn txnDetails = clientDAO.getClientTxn(txnId, clientId);
		model.addAttribute("creditAmt", clientDbo.getCredit());
		model.addAttribute("txnId", txnId);
		model.addAttribute("costPaid", (null != txnDetails ? txnDetails.getTxnCostPaid() : "0"));
		model.addAttribute("txnCost", req.getParameter("txncost"));
		
		model.addAttribute("Page", "payment");
		return "main";
	}
}
