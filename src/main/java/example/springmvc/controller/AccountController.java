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

@Controller
public class AccountController {

	@Autowired
	private UserStorage userStorage;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String getSignupPage() {
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView doSignup(UserRegistrationData userRegistrationData) {
		ModelAndView mav = new ModelAndView();
		Result<User, RegistrationError> result = this.userStorage.createNewUser(userRegistrationData);
		if (result.isSuccess()) {
			User user = result.getResult();
			mav.setViewName("index");
			mav.addObject("userId", user.getId());
		} else {
			RegistrationError error = result.getError(); 
			switch (error.getErrorType()) {
			case USER_ALREADY_EXISTS:
				mav.addObject("userId_error", error.getErrorMsg());
				break;
			case UNKNOWN_ERROR:
				mav.addObject("error", error.getErrorMsg());
				break;

			default:
				mav.addObject("error", error.getErrorMsg());
				break;
			}
			
			mav.setViewName("signup");
		}
		return mav;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getLoginPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView doLogin() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}
	
	public UserStorage getUserStorage() {
		return userStorage;
	}

	public void setUserStorage(UserStorage userStorage) {
		this.userStorage = userStorage;
	}
}
