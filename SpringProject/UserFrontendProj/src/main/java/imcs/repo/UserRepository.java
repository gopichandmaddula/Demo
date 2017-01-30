package imcs.repo;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import imcs.entity.User;

@Repository
public class UserRepository implements IUserRepository {
	private final String URL = "http://localhost:9090/UserBackendProj/users/";
	private final Logger logger = Logger.getLogger(UserRepository.class);

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserById(Long userId) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<User> responseEntity = restTemplate.getForEntity(URL+userId, User.class);
		
		return responseEntity.getBody();
	}

	@Override
	public List<User> getAllUsers() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List> responseEntity = restTemplate.getForEntity(URL, List.class);
		return (List<User>) responseEntity.getBody();
	}

	@Override
	public boolean save(User user) {
		logger.log(Priority.DEBUG, "save() called with  "+user);
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(URL+"save", user, Boolean.class);
		if(responseEntity.getStatusCode()==HttpStatus.CREATED)
			return responseEntity.getBody();
		else 
			return false;
	}

	@Override
	public User update(User user) {
		logger.log(Priority.INFO, "update() with "+user.toString());
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		HttpEntity<Object> entity = new HttpEntity<>(user, headers);
		
		ResponseEntity<User> responseEntity = restTemplate.exchange(URL+"update", HttpMethod.PUT, entity, User.class);
		if(responseEntity.getStatusCode()==HttpStatus.ACCEPTED) {
			return (User)responseEntity.getBody();
		}  else {
			return null;
		}
	}

	@Override
	public boolean delete(Long userId) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		HttpEntity<Object> entity = new HttpEntity<>(headers);
		ResponseEntity<Object> responseEntity = restTemplate.exchange(URL+"delete/"+userId, HttpMethod.DELETE, entity, Object.class);
		
		if(responseEntity.getStatusCode()==HttpStatus.ACCEPTED)
			return true;
		else 
			return false;
	}
}
