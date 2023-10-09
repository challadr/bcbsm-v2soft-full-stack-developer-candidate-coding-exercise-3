package com.customer.feedback.dao.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.customer.feedback.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.customer.feedback.dao.CustomerDao;
import com.customer.feedback.model.Customer;

@Repository
public class CustomerDaoImpl extends JdbcDaoSupport implements CustomerDao {
	
	@Autowired 
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}

	@Override
	public void insertFeedback(Feedback feedback) {
		String sql = "INSERT INTO Feedback " +
				"(id, username, comments, rating) VALUES (?, ?, ?, ?)" ;
		getJdbcTemplate().update(sql, new Object[]{
				feedback.getId(), feedback.getUsername(), feedback.getComments(), feedback.getRating()
		});
	}

	@Override
	public List<Customer> getAllCustomers(){
		String sql = "SELECT * FROM Customer";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		
		List<Customer> result = new ArrayList<Customer>();
		for(Map<String, Object> row:rows){
			Customer emp = new Customer();
			emp.setUsername((String)row.get("username"));
			emp.setRole((String)row.get("role"));
			result.add(emp);
		}
		
		return result;
	}

	@Override
	public Customer getCustomerByUserName(String username) {
		String sql = "SELECT * FROM Customer WHERE username = ?";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, username);

		return (Customer)getJdbcTemplate().queryForObject(sql, new Object[]{username}, new RowMapper<Customer>(){
			@Override
			public Customer mapRow(ResultSet rs, int rwNumber) throws SQLException {
				Customer emp = new Customer();
				emp.setUsername(rs.getString("username"));
				emp.setRole(rs.getString("role"));
				return emp;
			}
		});
	}

	@Override
	public List<Feedback> getFeedbacks(String username) {
		String sql = "SELECT * FROM Feedback WHERE username = ?";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, username);

		return rows.stream().map(row -> {
			Feedback fb = new Feedback();
			fb.setId((String)row.get("id"));
			fb.setUsername((String)row.get("username"));
			fb.setComments((String)row.get("comments"));
			fb.setRating(((BigDecimal)row.get("rating")).intValue());
			return fb;
		}).collect(Collectors.toList());
	}

}