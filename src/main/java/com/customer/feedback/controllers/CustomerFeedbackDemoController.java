package com.customer.feedback.controllers;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

import com.customer.feedback.model.Customer;
import com.customer.feedback.model.Feedback;
import com.customer.feedback.service.CustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

@Controller
public class CustomerFeedbackDemoController {

	@Autowired
	CustomerService customerService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView showFeedback(Principal principal) {
		boolean isUserAdmin = isUserAdmin(principal.getName());
		return isUserAdmin ? showFeedbacks(principal) : addFeedback();
	}

	@RequestMapping(value = "/addFeedback", method = RequestMethod.GET)
	public ModelAndView addFeedback() {
		return new ModelAndView("addFeedback", "feedback", new Feedback());
	}

	@RequestMapping(value = "/addFeedback", method = RequestMethod.POST)
	public ModelAndView processRequest(@ModelAttribute("feedback") Feedback feedback,
																		 Principal principal) {
		saveFeedback(feedback, principal.getName());
		return new ModelAndView("addFeedback", "feedback", new Feedback());
	}

	@RequestMapping("/showFeedbacks")
	public ModelAndView showFeedbacks(Principal principal) {
		List<Feedback> allFeedbacks = getAllFeedbacksByRole(principal);
		return new ModelAndView("showFeedbacks", "feedbacks", allFeedbacks);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("errorMsg", "Your username and password are invalid.");

		if (logout != null)
			model.addAttribute("msg", "You have been logged out successfully.");

		return "login";
	}

	private List<Feedback> getAllFeedbacksByRole(Principal principal) {
		Optional<Customer> custOpt = customerService.getCustomerByUserName(principal.getName());
		List<Customer> customers = StringUtils.equalsIgnoreCase(custOpt.get().getRole(), "ADMIN") ?
				customerService.getAllCustomers() : Collections.singletonList(custOpt.get());

		return customers.stream()
				.flatMap(cust -> customerService.getFeedbacks(cust.getUsername()).stream())
				.collect(Collectors.toList());
	}

	private boolean isUserAdmin(String principalUserName) {
		Optional<Customer> custOpt = customerService.getCustomerByUserName(principalUserName);
		return StringUtils.equalsIgnoreCase(custOpt.get().getRole(), "ADMIN");
	}

	@Transactional
	protected void saveFeedback(Feedback feedback, String username) {
		if (Objects.nonNull(feedback.getComments()) && Objects.nonNull(feedback.getRating())) {
			feedback.setId(UUID.randomUUID().toString());
			feedback.setUsername(username);
			customerService.insertFeedback(feedback);
		}
	}
}
