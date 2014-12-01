package com.ots.controller;

import java.util.ArrayList;
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
import com.ots.beans.Txn;
import com.ots.dao.ClientDAO;

@Controller
@RequestMapping("txnapproval.html")
public class ClientTxnApprovalController {

	@Autowired
	ClientDAO clientDAO;
	
	private static final String MODE_PAY = "pay";
	private static final String MODE_CANCEL = "cancel";
	private static final Logger log = Logger.getLogger(ClientTxnApprovalController.class);
	
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
		
		HttpSession session = req.getSession();
		try{
			String clientId = (String)session.getAttribute("clientId");
			String payCancel = req.getParameter("approvecancel");
			log.debug("Payment/Cancel: "+payCancel);
			String[] strCheckBoxValue = req.getParameterValues("checkbox[]");
			List<Integer> txnIdList = new ArrayList<Integer>();
			if (strCheckBoxValue != null) {
				Integer i;
				for(String s : strCheckBoxValue){
					i = Integer.parseInt(s);
					txnIdList.add(i);
				}
			}
			if(payCancel.equals(MODE_CANCEL)){
				//Redirect to Txn List page after deleting
				clientDAO.cancelTxn(txnIdList, clientId, clientId);
				
				List<Txn> txnList = clientDAO.ViewPendingTxn(clientId);
				log.debug("List size: "+txnList.size());
				model.addAttribute("txnlist", txnList);
				model.addAttribute("Page", "clienttxn");
			} else if(payCancel.equals(MODE_PAY)){
				//Redirect to Payment page
				int txnId = txnIdList.get(0);
				Txn txnDetails = clientDAO.getClientTxn(txnId, clientId);
				ClientDbo clientDbo = clientDAO.ViewOilCashReserves(clientId);
				
				model.addAttribute("creditAmt", clientDbo.getCredit());
				model.addAttribute("txnId", txnId);
				model.addAttribute("costPaid", (null != txnDetails ? txnDetails.getTxnCostPaid() : "0"));
				model.addAttribute("txnCost", txnDetails.getTxnCost());
				
				model.addAttribute("Page", "payment");
			}
		} catch(Exception e){
			log.error("Exception in txnapproval");
		}
		
		return "main";
	}
}
