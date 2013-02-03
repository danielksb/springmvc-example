package example.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import example.springmvc.data.UserRegistrationData;
import example.springmvc.data.UserStorage;

@Controller
public class AccountController {
	
	private UserStorage userStorage;

	@RequestMapping(value="/signup", method = RequestMethod.GET)
	public ModelAndView getSignupPage(UserRegistrationData userRegistrationData) {
		this.userStorage.create(userRegistrationData);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("signup");
		return mav;
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView getLoginPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
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
