package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pojo.Customer;
import pojo.Item;
import pojo.CustomerOrder;

public class AppUtil {
	public static List<Customer> getCustomersData() {
		List<Customer> list = new ArrayList<>();
		
		Customer customer1 = new Customer(null, "Mark", "Henry");
		Customer customer2 = new Customer(null, "Cristiano", "Ronaldo");
		
		list.add(customer1);
		list.add(customer2);
		
		return list;
	}
	
	public static CustomerOrder createOrder(Customer customer) {
		CustomerOrder order = new CustomerOrder();
		
		Random random = new Random();
		int set = random.nextInt(1);
		
		List<Item> items = createItemList(order, set);
		
		Integer orderTotal = 0;
		for(Item item : items) {
			orderTotal = item.getPrice();
		}
		
		order.setCustomer(customer);
		order.setOrderId(null);
		order.setDescription("Order "+set);
		order.setOrderTotal(orderTotal);
		order.setItems(items);
		
		return order;
	}
	
	private static List<Item> createItemList(CustomerOrder order, int itemSet) {
		List<Item> items = new ArrayList<>();
		
		if(itemSet == 0) {
			Item item1=new Item(null,5,"Candy Bar",2);
			Item item2=new Item(null, 50, "Headset", 1);
			
			item1.setOrder(order);
			item2.setOrder(order);
			
			items.add(item1);items.add(item2);
		} else {
			Item item1=new Item(null,75,"Mattress",1);
			Item item2=new Item(null, 199, "Phone", 3);
			
			item1.setOrder(order);
			item2.setOrder(order);
			
			items.add(item1);items.add(item2);
		}
		
		return items;
	} 
}
