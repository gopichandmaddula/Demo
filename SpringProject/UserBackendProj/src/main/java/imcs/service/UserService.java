package imcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imcs.entity.User;
import imcs.repo.IUserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Override
	public User getUserByUsername(String username) {
		return userRepository.getUserByUsername(username);
	}

	@Override
	public User getUserById(Long userId) {
		return userRepository.getUserById(userId);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.getAllUsers();
	}

	@Override
	public boolean save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User update(User user) {
		return userRepository.update(user);
	}

	@Override
	public boolean delete(Long userId) {
		return userRepository.delete(userId);
	}

}
