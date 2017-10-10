package demo.spring.angular.auth.web.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import demo.spring.angular.auth.web.request.SignupRequest;
import demo.spring.angular.auth.web.validation.PasswordMatches;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public void initialize(final PasswordMatches constraintAnnotation) {
		// Do nothing
	}

	@Override
	public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
		final SignupRequest signupRequest = (SignupRequest) obj;
		return signupRequest.getPassword().equals(signupRequest.getMatchingpassword());
	}

}
