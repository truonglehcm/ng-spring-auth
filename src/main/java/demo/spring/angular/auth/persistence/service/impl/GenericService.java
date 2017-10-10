package demo.spring.angular.auth.persistence.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.repository.JpaRepository;

import demo.spring.angular.auth.persistence.service.IGenericService;
import demo.spring.angular.auth.web.exception.ServiceException;

public class GenericService<T, ID extends Serializable> implements IGenericService<T, ID> {

    @Autowired
    private JpaRepository<T, ID> repository;

    @Autowired
    protected DozerBeanMapper mapper;
    
    @Autowired
    protected MessageSource messageSource;
    
    @PersistenceContext
    protected EntityManager entityManager;

    protected Class<T> entityClass;

    public GenericService() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

	public T findOne(ID id) throws ServiceException {
		try {
			return repository.findOne(id);
		} catch (Exception e) {
			throw new ServiceException(entityClass.getName() + "Service --> findOne()", e);
		}
    }

    public List<T> findAll() throws ServiceException {
    	try {
    		return repository.findAll();
		} catch (Exception e) {
			throw new ServiceException(entityClass.getName() + "Service --> findAll()", e);
		}
    }
    
    public void saveAndFlush(T t) throws ServiceException{
    	try {
    		 repository.saveAndFlush(t);
		} catch (Exception e) {
			throw new ServiceException(entityClass.getName() + "Service --> saveAndFlush()", e);
		}
    }
    
    public void save(T t) throws ServiceException {
    	try {
    		repository.save(t);
		} catch (Exception e) {
			throw new ServiceException(entityClass.getName() + "Service --> save()", e);
		}
    }

    public void save(List<T> t) throws ServiceException {
    	try {
    		repository.save(t);
		} catch (Exception e) {
			throw new ServiceException(entityClass.getName() + "Service --> save()", e);
		}
	}
    
    public void saveAndFlush(List<T> t) throws ServiceException {
    	try {
    		repository.save(t);
		} catch (Exception e) {
			throw new ServiceException(entityClass.getName() + "Service --> saveAndFlush()", e);
		}
	}
    
    public void delete(T t) throws ServiceException {
    	try {
    		repository.delete(t);
		} catch (Exception e) {
			throw new ServiceException(entityClass.getName() + "Service --> delete()", e);
		}
    }
    
    public void deleteById(ID id) throws ServiceException {
    	try {
    		repository.delete(id);
		} catch (Exception e) {
			throw new ServiceException(entityClass.getName() + "Service --> deleteById()", e);
		}
    }

}
