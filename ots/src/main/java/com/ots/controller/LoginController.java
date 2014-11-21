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

@Controller
@RequestMapping("login.html")
public class LoginController {

	@Autowired
	LoginDAO loginDAO;

	private static final Logger log = Logger.getLogger(LoginController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String loginPage(HttpServletRequest req, HttpServletResponse resp){
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
			UserInfo uInfo = loginDAO.checkLogin(info);
			if(uInfo == null){
				model.addAttribute("errorMsg", "Invalid Credentials");
				return "login";
			}
			model.addAttribute("uname", uInfo.getfName()+", "+uInfo.getlName());
			model.addAttribute("usrLvl", uInfo.getLvl());
			session.setAttribute("clientId", uInfo.getClientId());
			session.setAttribute("uname", uInfo.getfName()+", "+uInfo.getlName());
			
			return "success";
			
		} catch(Exception e){
			log.error("Error in Login Controller! "+ e);
		}
		
		return "login";
	}
}
