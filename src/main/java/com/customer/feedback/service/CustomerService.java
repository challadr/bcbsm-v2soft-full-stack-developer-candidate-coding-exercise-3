package com.customer.feedback.service;

import java.util.List;
import java.util.Optional;

import com.customer.feedback.model.Feedback;
import com.customer.feedback.model.Customer;

public interface CustomerService {
	void insertFeedback(Feedback feedback);
	List<Customer> getAllCustomers();
	Optional<Customer> getCustomerByUserName(String userName);
	List<Feedback> getFeedbacks(String username);
}