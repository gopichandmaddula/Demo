package repo.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import pojo.Customer;
import pojo.Item;
import repo.ItemsRepo;
import util.HibernateUtil;

public class ItemsRepoImpl implements ItemsRepo {
	final static Logger logger = Logger.getLogger(ItemsRepoImpl.class);
	private SessionFactory sessionFactory;
	
	public ItemsRepoImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public List<Item> getItemsOfCustomer(Customer customer) {
		logger.log(Level.INFO, "getItemsOfCustomer() start");
		Session session = sessionFactory.openSession();
		
		Query query = session.getNamedQuery("findItemsOfCustomer");
		query.setParameter("customerId", customer.getCustomerId());
		
		logger.log(Level.INFO, "getItemsOfCustomer() end");
		return (List<Item>) query.list();
	}

}
