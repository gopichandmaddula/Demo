package repo;

import java.util.List;

import pojo.Customer;

public interface CustomerRepo {
	void save(Customer customer);
	Customer findOne(Integer customerId);
	List<Customer> findCustomersWithOrderGreaterThan(Integer totalValue);
}
