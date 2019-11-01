package com.tcoveney.dao;

import java.util.List;

import com.tcoveney.model.Customer;

public interface CustomerDao {
	public List<Customer> findAll();
	public Customer find(int id);
	public Integer insert(Customer customer);
	public void update(Customer customer);
	public void delete(int id);
	// Used to truncate customers table before seeding
	public void truncateCustomerTable();
}
