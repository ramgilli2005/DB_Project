package com.ots.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ots.beans.Oilprice;
import com.ots.dao.AdminDAO;

@Controller
@RequestMapping("adminupdoil.html")
public class AdminUpdOilController {
	
	@Autowired
	private AdminDAO adminDAO;
	
	private static final Logger log = Logger.getLogger(AdminUpdOilController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String loginPage(HttpServletRequest req, HttpServletResponse resp, 
			@ModelAttribute("model") ModelMap model){
		log.debug("Entering Client Home Controller GET");
		double oilPrice = adminDAO.getOilPrice();
		model.addAttribute("oilPrice", oilPrice);
		model.addAttribute("Page", "adminoil");
		return "main";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String verifyUser(HttpServletRequest req, HttpServletResponse resp,
			@ModelAttribute("model") ModelMap model) {
		Oilprice op = new Oilprice();
		op.setOp_price(Double.parseDouble(req.getParameter("oilprice_new")));
		adminDAO.updateOilPrice(op);
		model.addAttribute("Msg", "Updated Successfully");
		model.addAttribute("oilPrice", op.getOilprice());
		model.addAttribute("Page", "adminoil");
		return "main";
	}
}
