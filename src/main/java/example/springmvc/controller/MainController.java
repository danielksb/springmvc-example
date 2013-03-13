package example.springmvc.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * This controller is responsible for the main/index page of the web
 * application.
 * 
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
			// NOTE: Just because the principal is not null
			// it does not necessarily means, the user is correctly
			// login. It is possible that the user (session) is deleted on
			// the server but the browser is still sending the session cookie.
			// Let's just ignore that here for the sake of simplicity.
			// One way to deal with this issue would be to check if the userId
			// really exists in the UserStorage and then quit the session
			// if that is not the case.
			String userId = principal.getName();
			mav.addObject("name", userId);
			mav.addObject("isUserLoggedIn", true);
		}
		return mav;
	}

}
