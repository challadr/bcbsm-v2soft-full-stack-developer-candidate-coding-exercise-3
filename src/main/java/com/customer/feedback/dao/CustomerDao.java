package com.customer.feedback.dao;

import java.util.List;

import com.customer.feedback.model.Feedback;
import com.customer.feedback.model.Customer;

public interface CustomerDao {
	List<Customer> getAllCustomers();
	Customer getCustomerByUserName(String userName);
	void insertFeedback(Feedback feedback);
	List<Feedback> getFeedbacks(String username);

}