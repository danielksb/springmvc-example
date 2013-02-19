package example.springmvc.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	public ModelAndView getIndexPage() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userId = auth.getName();
		ModelAndView mav = new ModelAndView("index");
		
		if (userId == null) {
			mav.addObject("name", "World");
			mav.addObject("isUserLoggedIn", false);
		} else {
			mav.addObject("name", userId);
			mav.addObject("isUserLoggedIn", true);
		}
		return mav;
	}

}
