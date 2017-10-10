package demo.spring.angular.auth.persistence.service;

import java.io.Serializable;
import java.util.List;

import demo.spring.angular.auth.web.exception.ServiceException;

public interface IGenericService <T, ID extends Serializable> {
    T findOne(ID id) throws ServiceException;
    List<T> findAll() throws ServiceException;
    void save(T t) throws ServiceException;
    void saveAndFlush(T t) throws ServiceException;
    void save(List<T> t) throws ServiceException;
    void saveAndFlush(List<T> t) throws ServiceException;
    void delete(T t) throws ServiceException;
    void deleteById(ID id) throws ServiceException;
}
