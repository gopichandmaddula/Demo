package imcs.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import imcs.entity.Authority;
import imcs.entity.User;
import imcs.service.UserService;
import imcs.util.StaticCache;
import imcs.validator.UserValidator;

@Controller
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidator userValidator;

	@InitBinder
	protected void init(WebDataBinder binder) {
		binder.addValidators(userValidator);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@ModelAttribute
	public User regUser(ModelMap modelMap) {
		modelMap.addAttribute("authoritiesList", StaticCache.getAuthority());
		
		User regUser = new User();
		return regUser;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public ModelAndView getSaveUser() {
		ModelAndView modelAndView = new ModelAndView("register");
		modelAndView.addObject("regUser", new User());
		return modelAndView;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute("regUser") @Valid User regUser, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView("register");

		if (!bindingResult.hasErrors()) {
			boolean success = userService.save(regUser);
			if (success) {
				modelAndView.addObject("regUser", new User());
				modelAndView.addObject("message", "Registration Successful!!");
			} else {
				modelAndView.addObject("message", "Registration Unsuccessful!!");
			}
		}

		return modelAndView;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getAllUsers(Model model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);

		return "displayUsers";
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ModelAndView getUserInfo(@PathVariable("userId") String userId, Model model) {
		Long id = Long.parseLong(userId);
		
		ModelAndView modelAndView = new ModelAndView("userInfo");

		User user = userService.getUserById(id);
		if (user != null) {
			modelAndView.addObject("user", user);
			modelAndView.addObject("authorities", getUserAuths(user.getCredentials().getAuthorities()));
		} else {
			modelAndView.addObject("message", "No user found with User ID : "+userId);
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{userId}", method=RequestMethod.GET)
	public String deleteUser(@PathVariable("userId") String userId, Model model) {
		Long id = Long.parseLong(userId);
		boolean result = userService.delete(id);
		if(result) {
			model.addAttribute("message", userId+" deleted successfully.");
		} else {
			model.addAttribute("message", userId+" couldn't be found");
		}		
		
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "displayUsers";
	}
	
	@RequestMapping(value="/update/{userId}", method=RequestMethod.GET)
	public ModelAndView getUpdateUser(@PathVariable("userId") String userId) {
		ModelAndView modelAndView = new ModelAndView("update");
		Long id = Long.parseLong(userId);
		
		User user = userService.getUserById(id);
		if(user!=null) modelAndView.addObject("user", user);
		else modelAndView.addObject("message", "No such user found!!");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/update/{userId}", method=RequestMethod.POST)
	public ModelAndView updateUser(@PathVariable("userId") String userId,
				@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
		Long id = Long.parseLong(userId);
		ModelAndView modelAndView = new ModelAndView("update");
		
		user.setUserId(id);
		if(!bindingResult.hasErrors()) {
			User updatedUser = userService.update(user);
			if(updatedUser!=null) {
				modelAndView.addObject("user", new User());
				modelAndView.addObject("message", "User updated successfully");
			} else {
				modelAndView.addObject("user", user);
				modelAndView.addObject("message", "User couldn't be updated");
			}
		}
		
		return modelAndView;
	}

	private String getUserAuths(List<Authority> authorities) {
		StringBuilder sb = new StringBuilder();
		for (Authority authority : authorities) {
			sb.append(authority.getAuthority());
			sb.append(",");
		}
		return sb.toString();
	}
}
