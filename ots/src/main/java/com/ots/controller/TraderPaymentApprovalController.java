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

import com.ots.beans.PaymentForTxn;
import com.ots.dao.TraderDAO;

@Controller
@RequestMapping("trdpymntapproval.html")
public class TraderPaymentApprovalController {

	@Autowired
	TraderDAO traderDAO;
	
	private static final Logger log = Logger.getLogger(TraderPaymentApprovalController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String loginPage(HttpServletRequest req, HttpServletResponse resp, 
			@ModelAttribute("model") ModelMap model){
		log.debug("Entering Txn Payment Controller GET");
		List<PaymentForTxn> ptlist= traderDAO.getPendingPaymentApprovals();
		model.addAttribute("pymntlist", ptlist);
		model.addAttribute("Page", "pymntapproval");
		return "main";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String verifyUser(HttpServletRequest req, HttpServletResponse resp,
			@ModelAttribute("model") ModelMap model) {
		log.debug("Entering Txn Payment Controller POST");
		HttpSession session = req.getSession();
		try{
			String traderId = (String)session.getAttribute("clientId");
			String payCancel = req.getParameter("approvecancel");
			log.debug("Payment: "+payCancel);
			String[] strCheckBoxValue = req.getParameterValues("checkbox[]");
			List<Integer> pIdList = new ArrayList<Integer>();
			if (strCheckBoxValue != null) {
				Integer i;
				for(String s : strCheckBoxValue){
					i = Integer.parseInt(s);
					pIdList.add(i);
				}
			}
			traderDAO.approvePymnt(pIdList, traderId);
			List<PaymentForTxn> ptlist= traderDAO.getPendingPaymentApprovals();
			model.addAttribute("pymntlist", ptlist);
			model.addAttribute("Page", "pymntapproval");
		} catch(Exception e){
			log.error("Error in approving payment"+e);
			model.addAttribute("errorMsg", "Unable to approve payment!!!");
		}
		
		return "main";
	}
}
