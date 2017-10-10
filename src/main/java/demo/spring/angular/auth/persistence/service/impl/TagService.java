package demo.spring.angular.auth.persistence.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.spring.angular.auth.persistence.entity.Tag;
import demo.spring.angular.auth.persistence.repository.TagRepository;
import demo.spring.angular.auth.persistence.service.ITagService;
import demo.spring.angular.auth.utils.AuthoritiesConstants;
import demo.spring.angular.auth.utils.MessageConstant;
import demo.spring.angular.auth.utils.SecurityUtils;
import demo.spring.angular.auth.web.exception.NotFoundException;
import demo.spring.angular.auth.web.exception.ServiceException;
import demo.spring.angular.auth.web.request.TagRequest;
import demo.spring.angular.auth.web.response.TagResponse;

@Service
@Transactional
public class TagService extends GenericService<Tag, Long> implements ITagService {
	
	@Autowired
	private TagRepository tagRepository;
	
	@Override
	public TagResponse createTag(TagRequest tagReq) throws ServiceException {
		try {
			if(!SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {
				String messageError = messageSource.getMessage(MessageConstant.ACCESS_DENIEL, null, Locale.US);
				throw new AccessDeniedException(messageError);
			}
	        Tag tag = new Tag();
	        tag.setName(tagReq.getName());
	        tag.setVisible(tagReq.getVisible());
	        tagRepository.save(tag);
	        
	        return mapper.map(tag, TagResponse.class);
		} catch (Exception e) {
			throw new ServiceException("TagService --> createTag()", e);
		}
	}

	@Override
	public void updateTag(TagRequest tag) throws ServiceException {
		try {
			if(!SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {
				String messageError = messageSource.getMessage(MessageConstant.ACCESS_DENIEL, null, Locale.US);
				throw new AccessDeniedException(messageError);
			}
			Tag oldTag = tagRepository.findOne(tag.getId());
	    	if (Objects.isNull(oldTag)) {
	    		throw new NotFoundException("Tag is not found!!!");
	    	}
	    	oldTag.setName(tag.getName());
	    	oldTag.setVisible(tag.getVisible());
	    	tagRepository.save(oldTag);
		} catch (Exception e) {
			throw new ServiceException("TagService --> updatePost()", e);
		}
	}

	@Override
	public void deleteTag(Long id) throws ServiceException {
		try {
			if(!SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {
				String messageError = messageSource.getMessage(MessageConstant.ACCESS_DENIEL, null, Locale.US);
				throw new AccessDeniedException(messageError);
			}
			tagRepository.delete(id);
		} catch (Exception e) {
			throw new ServiceException("TagService --> deleteTag()", e);
		}
	}

	@Override
	public TagResponse findTagById(Long id) throws ServiceException {
		try {
			Tag tag = tagRepository.findOne(id);
			if (Objects.isNull(tag)) {
				throw new NotFoundException("Can not found Tag with id: " + id);
			}
			return mapper.map(tag, TagResponse.class);
		} catch (Exception e) {
			throw new ServiceException("TagService --> findTagById()", e);
		}
	}

	@Override
	public List<TagResponse> findAllTag() throws ServiceException {
		return CollectionUtils.emptyIfNull(tagRepository.findAll())
				.stream().map(u -> mapper.map(u, TagResponse.class))
				.collect(Collectors.toList());
	}
}
