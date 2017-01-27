package imcs.repo;

import java.util.List;

import imcs.entity.User;

public interface IUserRepository {
	User getUserByUsername(String username);

	User getUserById(Long userId);

	List<User> getAllUsers();

	boolean save(User user);

	User update(User user);

	boolean delete(Long userId);
}
