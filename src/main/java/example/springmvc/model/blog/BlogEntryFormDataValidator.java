package example.springmvc.model.blog;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class BlogEntryFormDataValidator implements Validator {

	@Override
	public boolean supports(Class<?> formData) {
		return BlogEntryFormData.class.isAssignableFrom(formData);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "error.fieldRequired", "Field is required.");
	}

}
