package example.springmvc.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import example.springmvc.model.users.User;
import example.springmvc.model.users.UserRegistrationData;
import example.springmvc.model.users.UserRegistrationDataValidator;
import example.springmvc.model.users.UserService;

/**
 * The AccountController handles all requests for signing up, logging in
 * and logging out.
 * 
 * @author Daniel
 *
 */
@Controller
public class AccountController {
	
	@Autowired
	private UserService userService;

	/**
	 * Displays the signup form.
	 * @return
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView getSignupPage() {
		ModelAndView mav = new ModelAndView();
		UserRegistrationData userRegistrationData = new UserRegistrationData();
		mav.addObject("userRegistrationData", userRegistrationData);
		mav.setViewName("signup");
		return mav;
	}

	/**
	 * Handles requests to create a new user and runs some very basic
	 * validation checks on the uploaded data.
	 * @param userRegistrationData
	 * 		uploaded account data as defined by the user 
	 * @return
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView doSignup(UserRegistrationData userRegistrationData, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		
		new UserRegistrationDataValidator().validate(userRegistrationData, bindingResult);
		if (!bindingResult.hasErrors()) {
			User user = this.userService.createNewUser(userRegistrationData);
			if (user != null) {
				mav.setViewName("redirect:/");
				return mav;
			} else {
				bindingResult.reject(null, "error.user_already_exists");
			}
		}
		
		mav.setViewName("signup");
		return mav;
	}

	/**
	 * Displays the login form.
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getLoginPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	
	/**
	 * Displays the login form.
	 * @return
	 */
	@RequestMapping(value = "/loginfailure", method = RequestMethod.GET)
	public ModelAndView getLoginFailurePage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		mav.addObject("login_error", true);
		return mav;
	}
	
	@RequestMapping(value = "/accountSettings")
	public ModelAndView getUserSettingsPage(Principal principal) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("accountSettings");
		
		if (principal != null) {
			String userId = principal.getName();
		
			User user = this.getUserService().getUserStorage().byId(userId);
			mav.addObject("user", user);
		}
		
		return mav;
	}
	
	// getters and setters
	
	public UserService getUserService() {
		return this.userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
