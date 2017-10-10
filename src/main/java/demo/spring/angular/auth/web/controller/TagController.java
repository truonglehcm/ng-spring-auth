package demo.spring.angular.auth.web.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import demo.spring.angular.auth.persistence.service.ITagService;
import demo.spring.angular.auth.utils.CommonUtils;
import demo.spring.angular.auth.utils.URIConstant;
import demo.spring.angular.auth.web.exception.ServiceException;
import demo.spring.angular.auth.web.request.TagRequest;
import io.swagger.annotations.Api;

@Api
@RestController
public class TagController {

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private ITagService tagService;

	@GetMapping(value = { URIConstant.TAGS_GET_BY_ID, URIConstant.ADMIN_FIND_TAG })
	public ResponseEntity<?> getTag(@PathVariable Long id) throws JsonProcessingException, ServiceException {
		ObjectWriter viewWriter = CommonUtils.getObjectWriter(mapper);
		return ResponseEntity.ok(viewWriter.writeValueAsString(tagService.findTagById(id)));
	}

	@GetMapping(value = { URIConstant.TAGS, URIConstant.ADMIN_TAGS })
	public ResponseEntity<?> getTags(HttpServletRequest request) throws JsonProcessingException, ServiceException {
		ObjectWriter viewWriter = CommonUtils.getObjectWriter(mapper);
		return ResponseEntity.ok(viewWriter.writeValueAsString(tagService.findAllTag()));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = { URIConstant.ADMIN_TAGS })
	public ResponseEntity<?> createTag(@Valid @RequestBody TagRequest tagReq) throws ServiceException, JsonProcessingException {
		ObjectWriter viewWriter = CommonUtils.getObjectWriter(mapper);
		return ResponseEntity.ok(viewWriter.writeValueAsString(tagService.createTag(tagReq)));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping(value = { URIConstant.ADMIN_TAGS })
	public ResponseEntity<?> updateTag(@RequestBody TagRequest tag) throws ServiceException {
		tagService.updateTag(tag);
		return ResponseEntity.ok(null);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(value = { URIConstant.ADMIN_DELETE_TAGS })
	public ResponseEntity<?> deleteTag(@PathVariable Long id) throws ServiceException {
		tagService.deleteTag(id);
		return ResponseEntity.ok(null);
	}
}
