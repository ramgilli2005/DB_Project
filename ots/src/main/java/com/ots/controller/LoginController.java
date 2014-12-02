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
import com.ots.beans.LoginInfo;
import com.ots.beans.Txn;
import com.ots.beans.TxnLog;
import com.ots.beans.UserInfo;
import com.ots.dao.AdminDAO;
import com.ots.dao.ClientDAO;
import com.ots.dao.LoginDAO;
import com.ots.dao.TraderDAO;

@Controller
@RequestMapping("login.html")
public class LoginController {

	@Autowired
	LoginDAO loginDAO;
	
	@Autowired
	ClientDAO clientDAO;
	
	@Autowired
	TraderDAO traderDAO;
	
	@Autowired
	AdminDAO adminDAO;

	private static final Logger log = Logger.getLogger(LoginController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String loginPage(HttpServletRequest req, HttpServletResponse resp, 
			@ModelAttribute("model") ModelMap model){
		log.debug("Entering Login Controller GET");
		HttpSession session = req.getSession();
		session.invalidate();
		return "login";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String verifyUser(HttpServletRequest req, HttpServletResponse resp,
			@ModelAttribute("model") ModelMap model) {
		
		HttpSession session = req.getSession();
		
		try{
			
			LoginInfo info = new LoginInfo();
			log.debug("User Name: " +req.getParameter("username"));
			info.setUserId(req.getParameter("username"));
			info.setPwd(req.getParameter("password"));
			
			String type = req.getParameter("type");
			if(type.equals("user")){
				UserInfo uInfo = loginDAO.checkLogin(info);
				if(uInfo == null){
					log.debug("Invalid credentials!!!");
					model.addAttribute("errorMsg", "Invalid Credentials!!!");
					return "login";
				}
				
				
				log.debug("Client ID: "+ uInfo.getClientId()); 
				String clientId = uInfo.getClientId();
				ClientDbo clientDbo = clientDAO.ViewOilCashReserves(clientId);
				log.debug((null != clientDbo ? clientDbo.getQuantiy() : "null obj"));
				model.addAttribute("curQty", (null != clientDbo ? clientDbo.getQuantiy() : "0"));
				model.addAttribute("uname", uInfo.getfName()+" "+(uInfo.getlName()==null ? "":uInfo.getlName()));
				model.addAttribute("usrLvl", uInfo.getLvl());
				session.setAttribute("clientId", uInfo.getClientId());
				session.setAttribute("uname", uInfo.getfName()+" "+(uInfo.getlName()==null ? "":uInfo.getlName()));
				session.setAttribute("userType", "customer");
				session.setAttribute("usrLvl", uInfo.getLvl());
				model.addAttribute("Page", "clienthome");
				
			} else if(type.equals("employee")){
				UserInfo uInfo = loginDAO.checkEmplLogin(info);
				if(uInfo == null){
					log.debug("Invalid credentials!!!");
					model.addAttribute("errorMsg", "Invalid Credentials");
					return "login";
				}
				if(uInfo.getUserType().equals("manager")){
					List<TxnLog> txnLogList = traderDAO.ViewTxns();
					log.debug("List size: " + txnLogList.size());
					model.addAttribute("txnlist", txnLogList);
					model.addAttribute("Page", "Mgrtxnhist");
				} else if(uInfo.getUserType().equals("trader")) {
					
					List<Txn> txnList = traderDAO.ViewPaidPendingTxn();
					model.addAttribute("txnlist", txnList);
					model.addAttribute("Page", "tamhome");
					log.debug("System ID: "+uInfo.getClientId());
					
				} else if(uInfo.getUserType().equals("admin")){
					double oilPrice = adminDAO.getOilPrice();
					model.addAttribute("oilPrice", oilPrice);
					model.addAttribute("Page", "adminoil");
				}
				
				model.addAttribute("uname", uInfo.getfName()+" "+(uInfo.getlName()==null ? "":uInfo.getlName()));
//				model.addAttribute("Page", "tamhome");
				session.setAttribute("clientId", uInfo.getClientId());
				session.setAttribute("uname", uInfo.getfName()+" "+(uInfo.getlName()==null ? "":uInfo.getlName()));
				session.setAttribute("userType", "system");
				session.setAttribute("Sys_Position", uInfo.getUserType());

			}
			
			return "main";
		} catch(Exception e){
			log.error("Error in Login Controller! "+ e);
		}
		
		return "login";
	}
}
