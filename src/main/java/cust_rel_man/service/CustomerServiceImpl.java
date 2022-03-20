package cust_rel_man.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import cust_rel_man.model.Customer;
import cust_rel_man.dao.CustomerDAO;

@Service
@EnableTransactionManagement
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDAO customerDAO;

	@Transactional
	public List<Customer> getCustomers() {		
		return customerDAO.getCustomers();
	}

	@Transactional
	public Customer findById(int id) {
		return customerDAO.findById(id);
	}

	@Transactional
	public void save(Customer customer) {
		customerDAO.save(customer);
	}

	@Transactional
	public void deleteById(int id) {
		customerDAO.deleteById(id);
	}

}
