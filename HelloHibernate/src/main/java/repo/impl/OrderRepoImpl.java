package repo.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import com.mysql.jdbc.log.Log4JLogger;

import pojo.CustomerOrder;
import pojo.Item;
import repo.OrderRepo;
import util.HibernateUtil;

public class OrderRepoImpl implements OrderRepo {
	final static Logger logger = Logger.getLogger(OrderRepoImpl.class);
	
	private SessionFactory sessionFactory = null;

	public OrderRepoImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void save(CustomerOrder order) {
		logger.log(Level.INFO, "save() start with "+order);
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		session.save(order);
		session.getTransaction().commit();

		logger.log(Level.INFO, "save() end.");
		session.close();
	}

	@Override
	public CustomerOrder findOne(Integer orderId) {
		Session session = sessionFactory.openSession();
		CustomerOrder order = (CustomerOrder) session.get(CustomerOrder.class, orderId);
		session.close();

		return order;
	}

	@Override
	public List<Item> getItemsByCriteria(String firstName, String description, Integer itemId) {
		logger.log(Level.INFO, "getItemsByCriteria() start with "+firstName+", "+description+", "+itemId);
		Session session = sessionFactory.openSession();

		Criteria itemCriteria = session.createCriteria(Item.class);
		Criteria orderCriteria = itemCriteria.createCriteria("order", "o");
		Criteria customerCriteria = orderCriteria.createCriteria("customer", "c");
		
		if(description!=null) {
			orderCriteria.add(Restrictions.like("description", "%" + description + "%"));
		}

		if (firstName != null) {
			customerCriteria.add(Restrictions.like("firstName", firstName));
		}
		
		if(itemId!=null) {
			itemCriteria.add(Restrictions.eq("itemId", itemId));
		}
		
		logger.log(Level.INFO, "getItemsByCriteria() end");
		return (List<Item>)orderCriteria.list();
	}

}
