package demo.spring.angular.auth.web.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import demo.spring.angular.auth.utils.TokenUtil;
import demo.spring.angular.auth.web.validation.TokenValid;

public class TokenValidator implements ConstraintValidator<TokenValid, String> {
	
	@Autowired
	private TokenUtil jwtTokenUtil;

	@Override
	public void initialize(final TokenValid constraintAnnotation) {
		// Do nothing
	}

	@Override
	public boolean isValid(final String token, final ConstraintValidatorContext context) {
		return (validateToken(token));
	}

	private boolean validateToken(final String token) {
		return jwtTokenUtil.isTokenExpired(token);
	}
}
