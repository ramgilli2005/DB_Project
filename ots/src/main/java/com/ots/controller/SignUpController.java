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

import com.ots.beans.CreditCard;
import com.ots.beans.UserInfo;
import com.ots.dao.CardDetailsDAO;
import com.ots.dao.SignUpDAO;

@Controller
@RequestMapping("signup.html")
public class SignUpController {

	@Autowired
	SignUpDAO signUpDAO;
	
	@Autowired
	CardDetailsDAO cardDAO;
	
	private static final Logger log = Logger.getLogger(SignUpController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String signUpPage(HttpServletRequest req, HttpServletResponse resp, 
			@ModelAttribute("model") ModelMap model){
		log.debug("Entering Login Controller GET");
		HttpSession session = req.getSession();
		session.invalidate();
		return "signup";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String verifyUser(HttpServletRequest req, HttpServletResponse resp,
			@ModelAttribute("model") ModelMap model) {
		
		try{
			UserInfo uInfo = new UserInfo();
			String ssn = req.getParameter("ssn");
			
			if(signUpDAO.checkUserExists(ssn)){
				model.addAttribute("errorMsg", "User Already Exists!!!");
				return "signup";
			}
			
			//Set User Details
			uInfo.setfName(req.getParameter("fname"));
			uInfo.setPassword(req.getParameter("confirmpwd"));
			uInfo.setlName(req.getParameter("lname"));
			uInfo.setSsn(ssn);
			uInfo.setPhoneNo(req.getParameter("phno"));
			uInfo.setMobNo(req.getParameter("mobNo"));
			uInfo.setAddress(req.getParameter("address"));
			uInfo.setCity(req.getParameter("city"));
			uInfo.setState(req.getParameter("state"));
			uInfo.setZip(req.getParameter("zip"));
			
			String clientId = signUpDAO.insertUserDetails(uInfo);
			
			//Set Client Card details
			CreditCard card = new CreditCard();
			card.setCardNo(req.getParameter("cardno"));
			card.setCardName(req.getParameter("cardname"));
			card.setExpMon(req.getParameter("month"));
			card.setExpYr(req.getParameter("year"));
			card.setCvv(req.getParameter("cvv"));
			card.setClientId(clientId);
			
			boolean insertSuccess = cardDAO.insertCardDetails(card);
			
			//Rollback user details in case of error
			if(!insertSuccess){
				log.debug("Rolling back data");
				boolean rollback = signUpDAO.deleteUserDetails(clientId);
				log.info("Data Rolled status: "+rollback+" for client: "+clientId);
			}
			
			model.addAttribute("userId", clientId);
			return "success";
			
		} catch(Exception e){
			model.addAttribute("errorMsg", "User could not be created due to Tech issues");
			log.error("Error in SignUp Controller! "+ e);
		}
		
		return "signup";
	}
}
