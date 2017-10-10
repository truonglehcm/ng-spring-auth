package demo.spring.angular.auth.persistence.service;

import java.util.List;

import demo.spring.angular.auth.persistence.entity.Tag;
import demo.spring.angular.auth.web.exception.ServiceException;
import demo.spring.angular.auth.web.request.TagRequest;
import demo.spring.angular.auth.web.response.TagResponse;

public interface ITagService extends IGenericService<Tag, Long> {
	TagResponse createTag(TagRequest tag) throws ServiceException;
    void updateTag(TagRequest tag) throws ServiceException;
    void deleteTag(Long id) throws ServiceException;
    TagResponse findTagById(Long id)  throws ServiceException;
    List<TagResponse> findAllTag()  throws ServiceException;
}
