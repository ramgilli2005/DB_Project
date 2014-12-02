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
import com.ots.beans.UserInfo;
import com.ots.dao.ClientDAO;
import com.ots.dao.TraderDAO;

@Controller
@RequestMapping("tradertxn.html")
public class TraderTransactController {
	
	@Autowired
	private TraderDAO traderDAO;
	
	@Autowired
	private ClientDAO clientDAO;
	
	private static final Logger log = Logger.getLogger(TraderTransactController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String loginPage(HttpServletRequest req, HttpServletResponse resp, 
			@ModelAttribute("model") ModelMap model){
		log.debug("Entering TraderTransactController GET");
		try{
			List<UserInfo> uInfoList = traderDAO.getAllClientId();
			model.addAttribute("clientIdList", uInfoList);
			model.addAttribute("Page", "tradertxn");
		} catch(Exception e){
			log.error("Error in Trader Transact"+e);
		}
		
		return "main";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String verifyUser(HttpServletRequest req, HttpServletResponse resp,
			@ModelAttribute("model") ModelMap model) {
		HttpSession session = req.getSession();
		try{
			String clientId = req.getParameter("selclientid");
			ClientDbo clientDbo = clientDAO.ViewOilCashReserves(clientId);
			log.debug((null != clientDbo ? clientDbo.getQuantiy() : "null obj"));
			model.addAttribute("clientId", clientId);
			model.addAttribute("curQty", (null != clientDbo ? clientDbo.getQuantiy() : "0"));
			model.addAttribute("Page", "trdmaketxn");
		} catch(Exception e){
			log.error("Error in Trader Transact POST"+e);
		}
		return "main";
	}
}
