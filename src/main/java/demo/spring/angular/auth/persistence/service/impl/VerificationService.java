package demo.spring.angular.auth.persistence.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.spring.angular.auth.persistence.entity.VerificationToken;
import demo.spring.angular.auth.persistence.repository.VerificationTokenRepository;
import demo.spring.angular.auth.persistence.service.IVerificationTokenService;
import demo.spring.angular.auth.web.exception.ServiceException;

@Service
@Transactional
public class VerificationService extends GenericService<VerificationToken, Long> implements IVerificationTokenService {
	
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;

	@Override
	public VerificationToken findByToken(String token) throws ServiceException {
		try {
			return verificationTokenRepository.findByToken(token);
		} catch (Exception e) {
			throw new ServiceException("VerificationService --> findByToken()", e);
		}
	}
}
