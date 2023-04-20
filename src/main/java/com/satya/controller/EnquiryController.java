package com.satya.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.satya.binding.AddEnquiryForm;
import com.satya.binding.DashboardData;
import com.satya.binding.LoginForm;
import com.satya.service.EnquiryService;

@Controller
public class EnquiryController {
	@Autowired
	private EnquiryService enqService;

	@GetMapping("/enquiry")
	public String enquiry(Model model) {
		model.addAttribute("enqData", new AddEnquiryForm());
		return "addenquiry";
	}

	@PostMapping("/enquiry")
	public String addEnquiry(@ModelAttribute("enqData") AddEnquiryForm enqData, HttpSession session, Model model) {
		boolean status = this.enqService.addEnquiry(enqData);
		DashboardData dashboard = this.enqService.getDashboard((LoginForm)session.getAttribute("currentUser"));
		if (dashboard != null) {
			session.setAttribute("dashData", dashboard);
		}
		if (status) {
			model.addAttribute("success", "Enquiry Successfully Added..");
		} else {
			model.addAttribute("failure", "Some Fields Are Empty..");
		}
		return "addenquiry";
	}

	@GetMapping("/enquiry/{id}")
	public String addEnquiry(@PathVariable("id") int id) {
		System.out.println(id);
		return "addenquiry";
	}

	@GetMapping("/enquiries")
	public String viewEnquiry() {
		return "viewenquiry";
	}
}
