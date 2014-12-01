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
import com.ots.beans.PaymentForTxn;
import com.ots.dao.ClientDAO;

@Controller
@RequestMapping("txnpayment.html")
public class PaymentTxnController {
	
	@Autowired
	ClientDAO clientDAO;

	private static final String MODE_CREDIT = "credit";
	private static final String MODE_CARD = "card";
	private static final Logger log = Logger.getLogger(PaymentTxnController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String loginPage(HttpServletRequest req, HttpServletResponse resp, 
			@ModelAttribute("model") ModelMap model){
		log.debug("Entering Txn Payment Controller GET");

		return "main";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String acceptPayment(HttpServletRequest req, HttpServletResponse resp,
			@ModelAttribute("model") ModelMap model) {
		log.debug("Entering Txn Payment Controller POST");
		HttpSession session = req.getSession();
		try{
			String clientId = (String)session.getAttribute("clientId");
			PaymentForTxn pt = new PaymentForTxn();
			pt.setClientId((String)session.getAttribute("clientId"));
			pt.setPaymentAmount(Double.parseDouble(req.getParameter("pymntamt")));
			pt.setTxnId(Integer.parseInt(req.getParameter("transactionId")));
			//This object contains credit amt
			ClientDbo clientDbo = clientDAO.ViewOilCashReserves(clientId);
			String pymntMode = req.getParameter("creditcard");
			if(pymntMode.equals(MODE_CARD)){
				clientDAO.Payment(pt);
			} else if(pymntMode.equals(MODE_CREDIT)){
				boolean trnSuccess = clientDAO.PayUsingCredit(clientDbo.getCredit(), pt);
				if(!trnSuccess){
					model.addAttribute("errorMsg", "Unable to Complete Payment!!!");
				}
			}
			
			model.addAttribute("Page", "payment_success");
		} catch(Exception e){
			//Redirect to payment page upon error
			log.error("Error in PaymentTxnController"+e);
			try{
				String clientId = (String)session.getAttribute("clientId");
				ClientDbo clientDbo = clientDAO.ViewOilCashReserves(clientId);
				model.addAttribute("creditAmt", clientDbo.getCredit());
				model.addAttribute("txnId", req.getParameter("transactionId"));
				model.addAttribute("txnCost", req.getParameter("txncost"));
			} catch(Exception ex){
				log.error("Error in Payment Txn Controller for fetching error page values"+ex);
			}
			model.addAttribute("Page", "payment");
		}
		
		return "main";
	}
}
