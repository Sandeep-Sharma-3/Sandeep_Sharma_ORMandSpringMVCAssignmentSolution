package cust_rel_man.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cust_rel_man.model.Customer;
import cust_rel_man.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService custService;
	
	@RequestMapping("/list")
	public String listCustomer(Model theModel) {
		List<Customer> customers = custService.getCustomers();
		theModel.addAttribute("Customers",customers);
		return "list-customers";
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Customer customer = new Customer();
		theModel.addAttribute("customer",customer);
		return "add-customer-form";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id, Model theModel) {
		Customer customer = custService.findById(id);
		theModel.addAttribute("customer",customer);
		return "add-customer-form";
	}
	
	@PostMapping("/save")
	public String saveCustomer(@RequestParam("id") int id, @RequestParam("firstName") String fisrtName, @RequestParam("lastName") String lastName, @RequestParam("email") String email) {
		
		Customer customer;
		
		if(id != 0) {
			customer = custService.findById(id);
			customer.setFirstName(fisrtName);
			customer.setLastName(lastName);
			customer.setEmail(email);
		}
		else {
			customer = new Customer(fisrtName, lastName, email);
		}
		custService.save(customer);
		
		return "redirect:/customer/list";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("customerId") int id) {
		custService.deleteById(id);
		return "redirect:/customer/list";
	}
}
