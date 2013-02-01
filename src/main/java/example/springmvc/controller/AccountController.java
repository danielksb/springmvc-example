package example.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {

	@RequestMapping(value="/signup", method = RequestMethod.GET)
	public ModelAndView getSignupPage() {
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
}
