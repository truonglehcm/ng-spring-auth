package demo.spring.angular.auth.persistence.service;

import demo.spring.angular.auth.persistence.entity.VerificationToken;
import demo.spring.angular.auth.web.exception.ServiceException;

public interface IVerificationTokenService extends IGenericService<VerificationToken, Long> {
	VerificationToken findByToken(String token) throws ServiceException;
}
