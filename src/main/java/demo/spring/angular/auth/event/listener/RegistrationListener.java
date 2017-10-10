package demo.spring.angular.auth.event.listener;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import demo.spring.angular.auth.event.OnRegistrationCompleteEvent;
import demo.spring.angular.auth.persistence.entity.User;
import demo.spring.angular.auth.persistence.service.IUserService;
import demo.spring.angular.auth.utils.CommonUtils;
import demo.spring.angular.auth.utils.URIConstant;
import demo.spring.angular.auth.web.exception.ServiceException;

@Component
public class RegistrationListener {

	@Autowired
	private IUserService userService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private JavaMailSender mailSender;

	@Async
	@EventListener
    public void handleUserEvent(OnRegistrationCompleteEvent event) throws ServiceException {
		User user = event.getUser();
		final String token = UUID.randomUUID().toString();
		HttpServletRequest httpReq = event.getHttpReq();
		
		// create token
		userService.createVerificationToken(user, token);

		String recipientAddress = user.getEmail();
		String subject = messageSource.getMessage("title.regist.confirm", null, httpReq.getLocale());
		String confirmationUrl = CommonUtils.getBaseUrl(httpReq) + URIConstant.REGIST_ACTIVE + token;
		String message = messageSource.getMessage("message.regist.success", null, httpReq.getLocale());

		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);
		email.setSubject(subject);
		email.setText(message + " \n" + confirmationUrl);
		mailSender.send(email);
    }
}
