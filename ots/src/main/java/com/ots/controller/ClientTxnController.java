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
import com.ots.dao.ClientDAO;

@Controller
@RequestMapping("clienttxn.html")
public class ClientTxnController {
	
	@Autowired
	ClientDAO clientDAO;
	
	private static final Logger log = Logger.getLogger(ClientTxnController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String loginPage(HttpServletRequest req, HttpServletResponse resp, 
			@ModelAttribute("model") ModelMap model){
		log.debug("Entering Client Txn Controller GET");
		HttpSession session = req.getSession();
		try{
			String clientId = (String)session.getAttribute("clientId");
			List<Txn> txnList = clientDAO.ViewPendingTxn(clientId);
			log.debug("List size: "+txnList.size());
			model.addAttribute("txnlist", txnList);
		} catch(Exception e){
			log.error("Error in fetching txns");
		}
		
		model.addAttribute("Page", "clienttxn");
		return "main";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String verifyUser(HttpServletRequest req, HttpServletResponse resp,
			@ModelAttribute("model") ModelMap model) {
		
		//Redirect to payment page for that txn
		model.addAttribute("Page", "");
		return "main";
	}
}
