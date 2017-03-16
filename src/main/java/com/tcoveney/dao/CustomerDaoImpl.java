package com.tcoveney.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tcoveney.controller.CustomerController;
import com.tcoveney.model.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao{
	private Log log = LogFactory.getLog(CustomerController.class);

	private JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

	@Override
	public List<Customer> list() {
		log.info("Called CustomerDaoImpl.list()");
		String sql = "select * from customers";

		List<Customer> customers = this.jdbcTemplate.query(
		        sql,
		        new RowMapper<Customer>() {
		            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		                Customer customer = new Customer();
		                customer.setId(rs.getInt("id"));
		                customer.setName(rs.getString("name"));
		                customer.setStreet(rs.getString("street"));
		                
		                log.info(customer.toString());
		                
		                return customer;
		            }
		        });
		
		return customers;
	}
    
    

}
