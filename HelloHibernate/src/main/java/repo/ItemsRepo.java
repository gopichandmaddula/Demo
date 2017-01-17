package repo;

import java.util.List;

import pojo.Customer;
import pojo.Item;

public interface ItemsRepo {
	List<Item> getItemsOfCustomer(Customer customer);
	
}
