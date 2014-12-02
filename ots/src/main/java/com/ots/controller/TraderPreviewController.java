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
import com.ots.util.TxnUtil;

@Controller
@RequestMapping("trdtxnpreview.html")
public class TraderPreviewController {

	@Autowired
	ClientDAO clientDAO;
	
	@Autowired
	TxnUtil util;
	
	private static final String COMMISION_TYPE_CASH = "CASH";
	private static final String COMMISION_TYPE_OIL = "OIL";
	
	private static final Logger log = Logger.getLogger(TraderPreviewController.class);
	
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
		double cur_qty = Double.parseDouble(req.getParameter("cur_qty"));
		String clientId = req.getParameter("clientId");
		log.debug("Client Id from textbox: "+clientId);
		String usrLvl = clientDAO.getUserLvl(clientId);
		String orderType = req.getParameter("ordertype");
		String commsnType = req.getParameter("commsntype");
		double commission = 0.0;
		double txnCost = 0.0;
		if(orderType.equals("SELL")){
			if((qty-cur_qty) < 0){
				model.addAttribute("clientId", clientId);
				ClientDbo clientDbo = clientDAO.ViewOilCashReserves(clientId);
				model.addAttribute("curQty", (null != clientDbo ? clientDbo.getQuantiy() : "0"));
				model.addAttribute("Page", "trdmaketxn");
				model.addAttribute("errorMsg", "You do not have sufficient OIL to SELL");
				return "main";
			}
		}
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
		
		model.addAttribute("Page", "trdtransact_preview");
		
		return "main";
	}
}
