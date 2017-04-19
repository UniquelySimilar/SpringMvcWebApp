package com.tcoveney.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.tcoveney.model.Customer;

public class CustomerDaoHibernateImpl implements CustomerDao {

	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Customer customer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Customer customer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

}
