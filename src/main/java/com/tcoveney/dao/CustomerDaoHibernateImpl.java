package com.tcoveney.dao;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tcoveney.model.Customer;

@Repository("customerDaoHibernate")
@Transactional
public class CustomerDaoHibernateImpl implements CustomerDao {
	private static final Logger logger = LogManager.getLogger(CustomerDaoHibernateImpl.class);

	private SessionFactory sessionFactory;
	
	@Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> findAll() {
		//logger.info("CUSTOMER DAO FINDALL");
		Session session = this.sessionFactory.getCurrentSession();
		List<Customer> customerList = session.createQuery("from Customer").list();
		return customerList;
	}

	@Override
	public Customer find(int id) {
		//logger.info("CUSTOMER DAO FIND for id = " + id);
		Session session = this.sessionFactory.getCurrentSession();
		// NOTE: load() uses a proxy, which was causing an exception in CustomerController.initBinder()
		Customer customer = (Customer) session.get(Customer.class, new Integer(id));
		return customer;
	}
	
	@Override
	public Integer insert(Customer customer) {
		//logger.info("CUSTOMER DAO INSERT");
		Session session = this.sessionFactory.getCurrentSession();
		session.save(customer);
		return customer.getId();
	}

	@Override
	public void update(Customer customer) {
		//logger.info("CUSTOMER DAO UPDATE");
		Session session = this.sessionFactory.getCurrentSession();
		customer.setUpdatedAt(new Date());
		session.update(customer);
	}

	@Override
	public void delete(int id) {
		//logger.info("CUSTOMER DAO DELETE");
		Session session = this.sessionFactory.getCurrentSession();		
		Customer customer = (Customer) session.load(Customer.class, new Integer(id));
		session.delete(customer);
	}
	
	// Used to truncate customers table before seeding
	@Override
	public void truncateCustomerTable() {
		//logger.debug("called truncateCustomerTable");
		Session session = this.sessionFactory.getCurrentSession();		
		session.createNativeQuery("truncate table customers").executeUpdate();
	}

}
