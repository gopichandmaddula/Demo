package imcs.validator;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import imcs.entity.User;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		if(clazz.equals(User.class)) return true;
		return false;
	}

	@Override
	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		
		ValidationUtils.rejectIfEmpty(errors, "name", "user.name.empty.err");
		ValidationUtils.rejectIfEmpty(errors, "dob", "user.dob.empty.err");
		ValidationUtils.rejectIfEmpty(errors, "phone", "user.phone.empty.err");
		ValidationUtils.rejectIfEmpty(errors, "email", "user.email.empty.err");
		
		if(!isValidEmail(user.getEmail())) {
			errors.rejectValue("email", "user.email.invalid.err");
		}
		
		if(!isValidUsername(user.getCredentials().getUsername())) {
			errors.rejectValue("credentials.username", "user.username.invalid.err");
		}
		
		if(!isValidPassword(user.getCredentials().getPassword())) {
			errors.rejectValue("credentials.password", "user.password.invalid.err");
		}
	}	
	
	private boolean isValidEmail(String email) {
		return email.matches("^\\D+\\w*@\\D+[.]{1}\\D+");
	}
	
	private boolean isValidUsername(String username) {
		if(StringUtils.isEmpty(username)) return false;
		if(username.length()<6) return false;
		return true;
	}
	
	private boolean isValidPassword(String password) {
		if(StringUtils.isEmpty(password)) return false;
		if(password.length()<6) return false;
		return true;
	}
}
