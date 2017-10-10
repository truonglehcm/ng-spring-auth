package demo.spring.angular.auth.persistence.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.spring.angular.auth.persistence.entity.Authority;
import demo.spring.angular.auth.persistence.enums.AuthorityName;
import demo.spring.angular.auth.persistence.repository.AuthorityRepository;
import demo.spring.angular.auth.persistence.service.IAuthorityService;
import demo.spring.angular.auth.web.exception.ServiceException;

@Service
@Transactional
public class AuthorityService extends GenericService<Authority, Long> implements IAuthorityService {
	
	@Autowired
	private AuthorityRepository authorityRepository;

	@Override
	public Authority findByName(AuthorityName name) throws ServiceException {
		try {
			return authorityRepository.findByName(name);
		} catch (Exception e) {
			throw new ServiceException("AuthorityService --> findByName()", e);
		}
	}
}
