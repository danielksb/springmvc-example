package example.springmvc.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * This controller is responsible for the main/index page of the web
 * application.
 * @author Daniel
 *
 */
@Controller
@RequestMapping("/")
public class MainController {
	
	/**
	 * Displays the web page by greeting the user with it's user name. If
	 * the current visitor is not logged in, we simply greet the world.
	 * @param sessionId
	 * 		as defined in the session cookie, this can be null if the
	 * 		user is not logged in yet
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getIndexPage(Principal principal) {
		ModelAndView mav = new ModelAndView("index");
		if (principal == null) {
			mav.addObject("name", "World");
			mav.addObject("isUserLoggedIn", false);
		} else {
			String userId = principal.getName();
			mav.addObject("name", userId);
			mav.addObject("isUserLoggedIn", true);
		}
		return mav;
	}

}
