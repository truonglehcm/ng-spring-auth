package demo.spring.angular.auth.persistence.service;

import demo.spring.angular.auth.persistence.entity.Authority;
import demo.spring.angular.auth.persistence.enums.AuthorityName;
import demo.spring.angular.auth.web.exception.ServiceException;

public interface IAuthorityService extends IGenericService<Authority, Long> {
	Authority findByName(AuthorityName name) throws ServiceException;
}
