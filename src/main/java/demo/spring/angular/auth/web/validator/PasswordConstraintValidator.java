package demo.spring.angular.auth.web.validator;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.passay.DigitCharacterRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.Rule;
import org.passay.RuleResult;
import org.passay.RuleResultDetail;
import org.passay.SpecialCharacterRule;
import org.passay.UppercaseCharacterRule;
import org.passay.WhitespaceRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import com.google.common.base.Joiner;
import demo.spring.angular.auth.web.validation.PasswordValid;

@Component
public class PasswordConstraintValidator implements ConstraintValidator<PasswordValid, String> {

	@Autowired
    private MessageSource messageSource;
	
	@Override
	public void initialize(PasswordValid arg0) {
		// Do nothing 
	}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		
		List<Rule> listRule = Arrays.asList(
				new UppercaseCharacterRule(1),
				new DigitCharacterRule(1),
				new SpecialCharacterRule(1), 
				new WhitespaceRule());
		
		PasswordValidator validator = new PasswordValidator(listRule);
		RuleResult result = validator.validate(new PasswordData(password));
		if (result.isValid()) {
			return true;
		}
		
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(Joiner.on(",").join(getMessageError(result)))
				.addConstraintViolation();
		
		return false;
	}

	private List<String> getMessageError(RuleResult result) {
		return CollectionUtils.emptyIfNull(result.getDetails())
				.stream()
				.filter(Objects::nonNull)
				.map(r -> getMessage(r))
				.collect(Collectors.toList());
	}
	
	private String getMessage(RuleResultDetail ruleResultDetail) {
		String message = StringUtils.EMPTY;
		switch (ruleResultDetail.getErrorCode()) {
		case UppercaseCharacterRule.ERROR_CODE:
			message = messageSource.getMessage("message.error.uppercase", null, LocaleContextHolder.getLocale());
			break;
		case DigitCharacterRule.ERROR_CODE:
			message = messageSource.getMessage("message.error.digit", null, LocaleContextHolder.getLocale());
			break;
		case SpecialCharacterRule.ERROR_CODE:
			message = messageSource.getMessage("message.error.special", null, LocaleContextHolder.getLocale());
			break;
		case WhitespaceRule.ERROR_CODE:
			message = messageSource.getMessage("message.error.whitespace", null, LocaleContextHolder.getLocale());
			break;
		default:
			break;
		}
		return message;
	}
	
}
