package service;

import java.util.List;

import user.User;

public interface UserService {
	boolean validateUser(String user, String password);
	
	User findOneUser(String userId);
	List<User> findAllUsers();
	boolean insertUser(User user);
}
