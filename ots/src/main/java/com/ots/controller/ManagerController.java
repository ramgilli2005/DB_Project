package com.ots.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ots.beans.AggregateInfo;
import com.ots.dao.ManagerDAO;

@Controller
@RequestMapping("Mgrtxnaggr.html")
public class ManagerController {
	@Autowired
	private ManagerDAO ManagerDAO;
	private static final Logger log = Logger.getLogger(ManagerController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String loginPage(HttpServletRequest req, HttpServletResponse resp,
			@ModelAttribute("model") ModelMap model) {
		log.debug("Entering Manager Controller GET");
		model.addAttribute("Page", "MgrtxnAggr");
		return "main";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String verifyUser(HttpServletRequest req, HttpServletResponse resp,
			@ModelAttribute("model") ModelMap model) {
		String type = req.getParameter("type");
		String startDate = req.getParameter("startdate");
		String endDate = req.getParameter("enddate");
		startDate += " 00:00:00";
		endDate += " 00:00:00";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			Date parsedDate = dateFormat.parse(startDate);
			Timestamp firstDate = new java.sql.Timestamp(parsedDate.getTime());
			parsedDate = dateFormat.parse(endDate);
			Timestamp lastDate = new java.sql.Timestamp(parsedDate.getTime());
			List<AggregateInfo> aggregateInfoList = ManagerDAO
					.CalculateAggregateInfo(firstDate, lastDate, type);
			model.addAttribute("aggrlist", aggregateInfoList);

		} catch (Exception e) {
			log.error("Error in " + e);
		}
		model.addAttribute("Page", "Mgrtxnaggrresults");
		return "main";
	}
}