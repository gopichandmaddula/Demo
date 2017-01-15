package service;

import java.sql.SQLException;
import java.util.List;

import user.DBEmployeeOperations;
import user.User;
import user.UserDbOperations;

public class UserServiceImpl implements UserService {
	UserDbOperations userOperations = new DBEmployeeOperations("jdbc:mysql://localhost:3306/user","root","root");

	public boolean validateUser(String userId, String password) {
		try {
			User user = userOperations.findOneUser(userId);

			if (user != null && user.getPassword().equals(password)) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public User findOneUser(String userId) {
		try {
			return userOperations.findOneUser(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> findAllUsers() {
		try {
			return userOperations.findAllUsers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insertUser(User user) {
		try {
			return userOperations.insertUser(user);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
