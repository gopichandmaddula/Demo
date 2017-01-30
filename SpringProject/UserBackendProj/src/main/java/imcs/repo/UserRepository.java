package imcs.repo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import imcs.entity.Authority;
import imcs.entity.User;

@Repository
public class UserRepository implements IUserRepository {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User getUserByUsername(String username) {
		List<User> users = getAllUsers();

		for (User user : users) {
			if (user.getCredentials().getUsername() == username) {
				return user;
			}
		}
		return null;
	}

	@Override
	public User getUserById(Long userId) {
		Session session = sessionFactory.openSession();
		User user = (User) session.get(User.class, userId);
		session.close();
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		Session session = sessionFactory.openSession();

		Query query = session.createQuery("FROM User");
		List<User> users = (List<User>) query.list();
		session.close();
		return users;
	}

	@Override
	public boolean save(User user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		user.getCredentials().setUser(user);

		for (Authority authority : user.getCredentials().getAuthorities()) {
			authority.setCredential(user.getCredentials());
		}

		Long userId = (Long) session.save(user);
		session.getTransaction().commit();
		session.close();
		if (userId != null && userId > 0)
			return true;
		else
			return false;
	}
	
	private void copyUser(User src, User dst) {
		dst.setName(src.getName());
		dst.setEmail(src.getEmail());
		dst.setDob(src.getDob());
		dst.setPhone(src.getPhone());
		dst.getCredentials().setUsername(src.getCredentials().getUsername());
		dst.getCredentials().setPassword(src.getCredentials().getPassword());
		
		dst.getCredentials().setAuthorities(src.getCredentials().getAuthorities());
		for(Authority authority : dst.getCredentials().getAuthorities()) {
			authority.setCredential(dst.getCredentials());
		}
	}

	@Override
	public User update(User user) {
		User getUser = getUserById(user.getUserId());
		
		copyUser(user, getUser);
				
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("DELETE FROM Authority a WHERE a.credential.credentials_id = :credentialsId");
		query.setParameter("credentialsId", getUser.getCredentials().getCredentials_id());
		
		query.executeUpdate();
		
		User updatedUser = (User) session.merge(getUser);
		
		session.getTransaction().commit();
		session.close();
		
		return updatedUser;
	}

	@Override
	public boolean delete(Long userId) {
		User userToDelete = getUserById(userId);
		if (userToDelete == null)
			return false;

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(userToDelete);
		session.getTransaction().commit();
		session.close();
		return true;
	}
}
