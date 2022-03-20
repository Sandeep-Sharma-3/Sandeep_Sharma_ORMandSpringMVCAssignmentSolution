package cust_rel_man.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import cust_rel_man.model.Customer;

@Repository
@EnableTransactionManagement
public class CustomerDAOImpl implements CustomerDAO {
	private Session session;
	private SessionFactory sessionFactory;
	
	@Autowired
	public CustomerDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;		
		try {
			this.session = this.sessionFactory.getCurrentSession();
		}
		catch(HibernateException e) {
			this.session = this.sessionFactory.openSession();
		}
	}
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		Transaction tx = this.session.beginTransaction();
		List<Customer> customers = session.createQuery("from Customer").list(); 
		tx.commit();
		return customers;
	}

	@Override
	@Transactional
	public Customer findById(int id) {
		Transaction tx = this.session.beginTransaction();
		Customer customer = session.get(Customer.class, id); 
		tx.commit();
		return customer;
	}

	@Override
	@Transactional
	public void save(Customer customer) {
		Transaction tx = this.session.beginTransaction();
		this.session.saveOrUpdate(customer); 
		tx.commit();
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		Transaction tx = this.session.beginTransaction();
		Customer customer = session.get(Customer.class, id);
		session.delete(customer);
		tx.commit();
	}
}
