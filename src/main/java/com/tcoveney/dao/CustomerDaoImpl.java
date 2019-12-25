package com.tcoveney.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.tcoveney.model.Customer;

@Repository("customerDaoJdbc")
public class CustomerDaoImpl implements CustomerDao{
	private Log log = LogFactory.getLog(CustomerDaoImpl.class);

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CustomerDaoImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

	@Override
	public List<Customer> findAll() {
		//log.info("Called CustomerDaoImpl.findAll()");
		String sql = "select * from customers";

		List<Customer> customers = this.namedParameterJdbcTemplate.query(sql, new CustomerMapper());
		
		return customers;
	}

	@Override
	public Customer find(int id) {
		String sql = "select * from customers where id = :id";
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
		Customer customer = this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new CustomerMapper());
		
		return customer;
	}
	
	private static final class CustomerMapper implements RowMapper<Customer> {
	    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
	    	customer.setFirstName(rs.getString("first_name"));
	    	customer.setLastName(rs.getString("last_name"));
	    	customer.setStreet(rs.getString("street"));
	    	customer.setCity(rs.getString("city"));
	    	customer.setState(rs.getString("state"));
	    	customer.setZipcode(rs.getString("zipcode"));
	    	customer.setHomePhone(rs.getString("home_phone"));
	    	customer.setWorkPhone(rs.getString("work_phone"));
	    	customer.setEmail(rs.getString("email"));
	    	
	        return customer;
	    }
	}
	
	@Override
	public Integer insert(Customer customer) {
		String sql = "INSERT INTO customers (first_name, last_name, street, city, state, zipcode, home_phone, work_phone, email, created_at, updated_at)"
				+ " VALUES(:first_name, :last_name, :street, :city, :state, :zipcode, :home_phone, :work_phone, :email, :created_at, :updated_at)";
		
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("first_name", customer.getFirstName())
			.addValue("last_name", customer.getLastName())
			.addValue("street", customer.getStreet())
			.addValue("city", customer.getCity())
			.addValue("state", customer.getState())
			.addValue("zipcode", customer.getZipcode())
			.addValue("home_phone", customer.getHomePhone())
			.addValue("work_phone", customer.getWorkPhone())
			.addValue("email", customer.getEmail())
			.addValue("created_at", new Timestamp(System.currentTimeMillis()))
			.addValue("updated_at", new Timestamp(System.currentTimeMillis()));
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.namedParameterJdbcTemplate.update(sql, mapSqlParameterSource, keyHolder);

		return (Integer)keyHolder.getKey();
		//return keyHolder.getKey().intValue();
	}

	@Override
	public void update(Customer customer) {
		String sql = "UPDATE customers SET first_name = :first_name, last_name = :last_name, street = :street, city = :city, state = :state, " +
				"zipcode = :zipcode, home_phone = :home_phone, work_phone = :work_phone, email = :email, " +
				"updated_at = :updated_at WHERE id = :id";

		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("first_name", customer.getFirstName())
			.addValue("last_name", customer.getLastName())
			.addValue("street", customer.getStreet())
			.addValue("city", customer.getCity())
			.addValue("state", customer.getState())
			.addValue("zipcode", customer.getZipcode())
			.addValue("home_phone", customer.getHomePhone())
			.addValue("work_phone", customer.getWorkPhone())
			.addValue("email", customer.getEmail())
			.addValue("updated_at", new Timestamp(System.currentTimeMillis()))
			.addValue("id", customer.getId());
		
		this.namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE from customers WHERE id = :id";
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
		this.namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	// Used to truncate customers table before seeding
	@Override
	public void truncateCustomerTable() {
		// TODO Auto-generated method stub
		
	}
    
}
