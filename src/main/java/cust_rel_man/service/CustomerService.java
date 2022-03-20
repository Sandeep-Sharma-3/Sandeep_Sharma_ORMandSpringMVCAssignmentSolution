package cust_rel_man.service;

import java.util.List;

import cust_rel_man.model.Customer;

public interface CustomerService {
	public List<Customer> getCustomers();
	public Customer findById(int id);
	public void save(Customer customer);
	public void deleteById(int id);
}
