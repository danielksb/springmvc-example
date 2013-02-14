package example.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import example.springmvc.data.Session;
import example.springmvc.data.SessionStorage;

/**
 * This controller is responsible for the main/index page of the web
 * application.
 * @author Daniel
 *
 */
@Controller
@RequestMapping("/")
public class MainController {

	
	@Autowired
	private SessionStorage sessionStorage;
	
	/**
	 * Displays the web page by greeting the user with it's user name. If
	 * the current visitor is not logged in, we simply greet the world.
	 * @param sessionId
	 * 		as defined in the session cookie, this can be null if the
	 * 		user is not logged in yet
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getIndexPage(@CookieValue(required=false, value="sessionId") String sessionId) {
		String userId = this.getUserId(sessionId);
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
	
	private String getUserId(String sessionId) {
		if (sessionId != null) {
			Session session = this.sessionStorage.getById(sessionId);
			if (session != null) {
				String userId = session.getUserId();
				return userId;
			}
		}
		return null;
	}
	
	public SessionStorage getSessionStorage() {
		return sessionStorage;
	}

	public void setSessionStorage(SessionStorage sessionStorage) {
		this.sessionStorage = sessionStorage;
	}

}
