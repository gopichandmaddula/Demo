package imcs.rest.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import imcs.entity.User;
import imcs.service.IUserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	private final static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getUser(@PathVariable String userId) {
		try {
			Long id = Long.parseLong(userId);
			User user = userService.getUserById(id);

			if (user == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
		} catch (NumberFormatException nfe) {
			logger.log(Priority.ERROR, nfe.getMessage());
			nfe.printStackTrace();

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT, consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> updateUser(@RequestBody User user) {
		User updatedUser = userService.update(user);
		return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE},
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> createUser(@RequestBody User user) {
		logger.log(Priority.INFO, "/users/save \n"+user.toString());
		boolean isSaved = userService.save(user);
		if(isSaved) {
			return new ResponseEntity<>(isSaved, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(isSaved, HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@RequestMapping(value="/delete/{userId}", method=RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId) {
		boolean isDeleted = userService.delete(userId);
		if(isDeleted) {
			return new ResponseEntity<>(isDeleted, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(isDeleted, HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
