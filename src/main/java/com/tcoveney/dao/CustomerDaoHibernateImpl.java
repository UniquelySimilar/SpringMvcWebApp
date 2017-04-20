package com.tcoveney.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tcoveney.model.Customer;

@Repository
@Transactional
public class CustomerDaoHibernateImpl implements CustomerDao {
	private Log log = LogFactory.getLog(CustomerDaoHibernateImpl.class);

	private SessionFactory sessionFactory;
	
	@Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Customer> customerList = session.createQuery("from Customer").list();
		return customerList;
	}

	@Override
	public Customer find(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		// NOTE: load() uses a proxy, which was causing an exception in CustomerController.initBinder()
		Customer customer = (Customer) session.get(Customer.class, new Integer(id));
		return customer;
	}
	
	@Override
	public int insert(Customer customer) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(customer);
		return customer.getId();
	}

	@Override
	public void update(Customer customer) {
		Session session = this.sessionFactory.getCurrentSession();
		// TODO: Call customer.setUpdatedAt()
		session.update(customer);
	}

	@Override
	public void delete(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Customer customer = (Customer) session.load(Customer.class, new Integer(id));
		session.delete(customer);
	}

}
