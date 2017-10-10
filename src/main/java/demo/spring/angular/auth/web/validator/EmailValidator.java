package demo.spring.angular.auth.web.validator;

import java.util.regex.Matcher;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import demo.spring.angular.auth.utils.SystemConstant;
import demo.spring.angular.auth.web.validation.EmailValid;

public class EmailValidator implements ConstraintValidator<EmailValid, String> {
	
	@Override
	public void initialize(final EmailValid constraintAnnotation) {
		// Do nothing
	}

	@Override
	public boolean isValid(final String username, final ConstraintValidatorContext context) {
		return (validateEmail(username));
	}

	private boolean validateEmail(final String email) {
		Matcher matcher = SystemConstant.EMAIL_PATTERN.matcher(email);
		return matcher.matches();
	}
}
