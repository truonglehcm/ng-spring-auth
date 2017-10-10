package demo.spring.angular.auth.persistence.service;

import demo.spring.angular.auth.web.exception.ServiceException;

public interface ICaptchaService {
    void processResponse(final String response) throws ServiceException;
}
