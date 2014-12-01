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

import com.ots.beans.TxnLog;
import com.ots.dao.ClientDAO;

@Controller
@RequestMapping("clienttxnhist.html")
public class ClientTxnHistController {
	@Autowired
	ClientDAO clientDAO;
	private static final Logger log = Logger
			.getLogger(ClientTxnHistController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String loginPage(HttpServletRequest req, HttpServletResponse resp,
			@ModelAttribute("model") ModelMap model) {
		log.debug("Entering Client Txn Hist Controller GET");
		HttpSession session = req.getSession();
		try {
			String clientId = (String) session.getAttribute("clientId");
			List<TxnLog> txnLogList = clientDAO.ViewTxns(clientId);
			log.debug("List size: " + txnLogList.size());
			model.addAttribute("txnlist", txnLogList);
		} catch (Exception e) {
			log.error("Error in fetching transaction history");
		}
		model.addAttribute("Page", "clienttxnhist");
		return "main";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String verifyUser(HttpServletRequest req, HttpServletResponse resp,
			@ModelAttribute("model") ModelMap model) {
		// Redirect to payment page for that txn
		model.addAttribute("Page", "");
		return "main";
	}
}