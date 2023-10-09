package com.customer.feedback.service.impl;

import java.util.List;
import java.util.Optional;

import com.customer.feedback.dao.CustomerDao;
import com.customer.feedback.model.Customer;
import com.customer.feedback.model.Feedback;
import com.customer.feedback.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao customerDao;

	@Override
	public void insertFeedback(Feedback feedback) {
		customerDao.insertFeedback(feedback);
	}

	public List<Customer> getAllCustomers() {
		return customerDao.getAllCustomers();
	}

	@Override
	public Optional<Customer> getCustomerByUserName(String userName) {
		return Optional.ofNullable(customerDao.getCustomerByUserName(userName));
	}

	@Override
	public List<Feedback> getFeedbacks(String userName) {
		return customerDao.getFeedbacks(userName);
	}
}