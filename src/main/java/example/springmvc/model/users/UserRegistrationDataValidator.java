package example.springmvc.model.users;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator class for the user registration data. Currently the validator
 * is called manually.
 * 
 *  TODO: inject validator automagically (also experimenting with JSR-303 ...)
 *  
 *  http://static.springsource.org/spring/docs/3.2.x/spring-framework-reference/html/validation.html#validation-beanvalidation
 * 
 * @author Daniel
 *
 */
public class UserRegistrationDataValidator implements Validator {

	@Override
	public boolean supports(Class<?> userRegistrationData) {
		return userRegistrationData.equals(UserRegistrationData.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "error.fieldRequired", "Field is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.fieldRequired", "Field is required.");
		UserRegistrationData userRegistrationData = (UserRegistrationData) obj;
		if (!userRegistrationData.getPassword().equals(
				userRegistrationData.getConfirmedPassword())) {
			errors.rejectValue("password", "error.passwords_dont_match", "The passwords don't match");
		}
	}

}
