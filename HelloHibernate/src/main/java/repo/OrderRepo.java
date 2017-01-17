package repo;

import java.util.List;

import pojo.CustomerOrder;
import pojo.Item;

public interface OrderRepo {
	void save(CustomerOrder order);
	CustomerOrder findOne(Integer orderId);
	List<Item> getItemsByCriteria(String firstName, String description, Integer itemId);
}
