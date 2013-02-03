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

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView getSignupPage(UserRegistrationData userRegistrationData) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("signup");
		try {
			if (this.userStorage.byId(userRegistrationData.getId()) == null) {
				this.userStorage.create(userRegistrationData);
				mav.addObject("success", true);
			} else {
				mav.addObject("success", false);
				mav.addObject("errorMsg", "USER_EXISTS_ALREADY");
			}
		} catch (Exception e) {
			mav.addObject("success", false);
			mav.addObject("errorMsg", "UNKNOWN_ERROR");
			mav.addObject("errorDetails", e.getMessage());
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
