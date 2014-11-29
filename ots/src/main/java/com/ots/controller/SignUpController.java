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

import com.ots.beans.LoginInfo;
import com.ots.beans.UserInfo;
import com.ots.dao.LoginDAO;
import com.ots.dao.SignUpDAO;

@Controller
@RequestMapping("signup.html")
public class SignUpController {

	@Autowired
	SignUpDAO signUpDAO;
	
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
				model.addAttribute("errorMsg", "User Already Exists");
				return "signup";
			}
			
			uInfo.setfName(req.getParameter("fname"));
			uInfo.setlName(req.getParameter("lname"));
			uInfo.setSsn(ssn);
			uInfo.setPhoneNo(req.getParameter("phno"));
			uInfo.setMobNo(req.getParameter("mobNo"));
			uInfo.setAddress(req.getParameter("address"));
			uInfo.setCity(req.getParameter("city"));
			uInfo.setState(req.getParameter("state"));
			uInfo.setZip(req.getParameter("zip"));
			
			return "login";
			
		} catch(Exception e){
			log.error("Error in Login Controller! "+ e);
		}
		
		return "signup";
	}
}
