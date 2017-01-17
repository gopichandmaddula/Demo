package imcs.tek.hibernate.HelloHibernate;

import java.util.List;
import java.util.Scanner;

import pojo.Customer;
import pojo.CustomerOrder;
import pojo.Item;
import repo.CustomerRepo;
import repo.ItemsRepo;
import repo.OrderRepo;
import repo.impl.CustomerRepoImpl;
import repo.impl.ItemsRepoImpl;
import repo.impl.OrderRepoImpl;
import util.AppUtil;

/**
 * Tek
 *
 */
public class App {

	CustomerRepo customerRepo = new CustomerRepoImpl();
	OrderRepo orderRepo = new OrderRepoImpl();
	ItemsRepo itemsRepo = new ItemsRepoImpl();

	public static void main(String[] args) {
		System.out.println("Hello World!");
		App app = new App();
		app.mainMenu();
	}

	private void showMenu() {
		System.out.println("\n***MENU***");
		System.out.println("1. Insert data");
		System.out.println("2. Display customers whose order total is greater than $100");
		System.out.println("3. Display items of a given customer");
		System.out.println("4. Display all the items with criteria (firstName, description, itemId) ");
		System.out.println("5. Exit");
	}

	public void mainMenu() {
		Scanner scanner = new Scanner(System.in);
		boolean flag = true;
		while (flag) {
			showMenu();
			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				loadData();
				System.out.println("Data inserted into database.");
				break;

			case 2:
				List<Customer> customers = customerRepo.findCustomersWithOrderGreaterThan(20);
				display(customers);
				break;

			case 3:
				System.out.println("\nEnter customer Id : ");
				int custId = scanner.nextInt();
				Customer customer = customerRepo.findOne(custId);
				if (customer != null) {
					List<Item> items = itemsRepo.getItemsOfCustomer(customer);
					display(items);
				} else {
					System.out.println("Customer not found with ID " + custId);
				}
				break;

			case 4:
				System.out.println("Enter firstName : ");
				String firstName = scanner.next();
				System.out.println("Enter Item description : ");
				String description = scanner.next();
				System.out.println("enter item id : ");
				Integer itemId = scanner.nextInt();

				List<Item> orders = orderRepo.getItemsByCriteria(firstName, description, itemId);
				display(orders);
				break;
			case 5:
				flag = false;
				break;
			}
		}
	}

	public <T> void display(List<T> list) {
		for (T t : list)
			System.out.println(t.toString());
	}

	public void loadData() {
		List<Customer> customers = AppUtil.getCustomersData();
		for (Customer customer : customers) {
			// customerRepo.save(customer);

			CustomerOrder order = AppUtil.createOrder(customer);

			orderRepo.save(order);
		}
	}
}
