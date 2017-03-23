package com.tcoveney.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
		                customer.setCity(rs.getString("city"));
		                customer.setState(rs.getString("state"));
		                customer.setZipcode(rs.getString("zipcode"));
		                customer.setHomePhone(rs.getString("home_phone"));
		                customer.setWorkPhone(rs.getString("work_phone"));
		                customer.setEmail(rs.getString("email"));
		                
		                //log.info(customer.toString());
		                
		                return customer;
		            }
		        });
		
		return customers;
	}

	@Override
	public int insert(Customer customer) {
		String sql = "INSERT INTO customers (name) VALUES(?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id"});
		            ps.setString(1, customer.getName());
		            return ps;
		        }
		    },
		    keyHolder);

		return keyHolder.getKey().intValue();
	}
    
}
