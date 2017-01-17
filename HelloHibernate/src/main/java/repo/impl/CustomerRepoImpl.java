package repo.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import pojo.Customer;
import repo.CustomerRepo;
import util.HibernateUtil;

public class CustomerRepoImpl implements CustomerRepo {
	static final Logger logger = Logger.getLogger(CustomerRepoImpl.class);
	
	private SessionFactory sessionFactory = null;

	public CustomerRepoImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public void save(Customer customer) {
		logger.log(Level.INFO, "save() start");
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		session.save(customer);
		session.getTransaction().commit();

		logger.log(Level.INFO, "save() end");
		session.close();
	}

	public Customer findOne(Integer customerId) {
		Session session = sessionFactory.openSession();
		
		return (Customer)session.get(Customer.class, customerId);
	}

	@Override
	public List<Customer> findCustomersWithOrderGreaterThan(Integer totalValue) {
		String queryString = "SELECT c FROM CustomerOrder o INNER JOIN o.customer c WHERE o.orderTotal > :totalValue";
		
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(queryString);
		query.setParameter("totalValue", totalValue);
		
		return (List<Customer>)query.list();
	}
}
