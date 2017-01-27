package imcs.rest.controller.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import imcs.entity.Authority;
import imcs.entity.Credentials;
import imcs.entity.User;

public class UserControllerTest {

	private static String URL = "http://localhost:8080/UserBackendProj/users/";

	private boolean skipTests = false;
	
	

	@Test
	@Ignore
	public void testCreateUser() {
		if (skipTests)
			return;

		RestTemplate restTemplate = new RestTemplate();
		
		String jsonString = "{"+
				"\"name\" : \"Harry\","+
				"\"dob\" : \"1995-12-25\","+
				"\"phone\" : \"+174586624512\","+
				"\"email\" : \"email@email.com\","+
				"\"credentials\" : {"+
				"\"username\" : \"messi\","+
				"	\"password\" : \"messi\","+
				"	\"authorities\" : ["+
				"		{	"+
				"			\"authority\":\"ROLE_USER\""+
				"		},"+
				"		{	"+
				"			\"authority\" : \"ROLE_ADMIN\""+
				"		}"+
				"	]"+
				"}"+
			"}";
		
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(jsonString, headers);
		
		ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(URL+"save", entity, Boolean.class);
		Boolean response = responseEntity.getBody();
		
		assertEquals(true, response);

	}

	@Test
	@Ignore
	public void testUpdateUsersName() {
		if(skipTests)
			return;
		
		RestTemplate restTemplate = new RestTemplate();
		
		String jsonString = "{"+
				"\"userId\": \"2\","+
				"\"name\" : \"Messi\","+
				"\"dob\" : \"1995-12-25\","+
				"\"phone\" : \"+174586624512\","+
				"\"email\" : \"email@email.com\","+
				"\"credentials\" : {"+
				"\"username\" : \"messi\","+
				"	\"password\" : \"messi\","+
				"	\"authorities\" : ["+
				"		{	"+
				"			\"authority\":\"ROLE_USER\""+
				"		},"+
				"		{	"+
				"			\"authority\" : \"ROLE_ADMIN\""+
				"		}"+
				"	]"+
				"}"+
			"}";
		
		User user1 = restTemplate.getForObject(URL+"1", User.class);
		user1.setName("Mark henry");
		
		restTemplate.put(URL+"update", user1);		
		
		ResponseEntity<User> responseEntity = restTemplate.getForEntity(URL+1, User.class);
		User user = (User) responseEntity.getBody();
		System.out.println(user);
		assertNotNull(user);
		assertEquals("Mark henry", user.getName());
		
	}
	
	@Test(expected=HttpClientErrorException.class)
	public void testDeleteUser() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(URL+"delete/"+1);
		
		User user = restTemplate.getForObject(URL+1, User.class);
		assertNull(user);
	}
}
