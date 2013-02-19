package example.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import example.springmvc.data.RegistrationError;
import example.springmvc.data.User;
import example.springmvc.data.UserRegistrationData;
import example.springmvc.data.UserStorage;
import example.springmvc.utils.Result;

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
	private UserStorage userStorage;

	/**
	 * Displays the signup form.
	 * @return
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String getSignupPage() {
		return "signup";
	}

	/**
	 * Handles requests to create a new user and runs some very basic
	 * validation checks on the uploaded data.
	 * @param userRegistrationData
	 * 		uploaded account data as defined by the user 
	 * @return
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView doSignup(UserRegistrationData userRegistrationData) {
		ModelAndView mav = new ModelAndView();
		Result<User, RegistrationError> result = this.userStorage
				.createNewUser(userRegistrationData);
		if (result.isSuccess()) {
			mav.setViewName("redirect:/");
		} else {
			RegistrationError error = result.getError();
			switch (error.getErrorType()) {
			case USER_ALREADY_EXISTS:
				mav.addObject("userId_error", error.getErrorMsg());
				break;
			default:
				mav.addObject("error", error.getErrorMsg());
				break;
			}

			// We want to prevent the user from filling out the whole form again,
			// so we add the registration data to the view. This allows us to
			// display the old values.
			mav.addObject("userRegistrationData", userRegistrationData);
			mav.setViewName("signup");
		}
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
	
	// getters and setters
	
	public UserStorage getUserStorage() {
		return userStorage;
	}

	public void setUserStorage(UserStorage userStorage) {
		this.userStorage = userStorage;
	}

}
