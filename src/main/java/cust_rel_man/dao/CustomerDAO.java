package cust_rel_man.dao;

import java.util.List;

import cust_rel_man.model.Customer;

public interface CustomerDAO {
	public List<Customer> getCustomers();
	public Customer findById(int id);
	public void save(Customer customer);
	public void deleteById(int id);
}
