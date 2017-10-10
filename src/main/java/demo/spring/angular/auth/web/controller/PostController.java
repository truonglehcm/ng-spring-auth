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

import demo.spring.angular.auth.persistence.service.IPostService;
import demo.spring.angular.auth.utils.CommonUtils;
import demo.spring.angular.auth.utils.URIConstant;
import demo.spring.angular.auth.web.exception.ServiceException;
import demo.spring.angular.auth.web.request.PostRequest;
import io.swagger.annotations.Api;

@Api
@RestController
public class PostController {

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private IPostService postService;

	@GetMapping(value = { URIConstant.POSTS_GET_BY_ID, URIConstant.ADMIN_FIND_POST })
	public ResponseEntity<?> getPost(@PathVariable Long id) throws JsonProcessingException, ServiceException {
		ObjectWriter viewWriter = CommonUtils.getObjectWriter(mapper);
		return ResponseEntity.ok(viewWriter.writeValueAsString(postService.getPostDetail(id)));
	}

	@GetMapping(value = { URIConstant.POSTS })
	public ResponseEntity<?> getPosts(HttpServletRequest request) throws JsonProcessingException, ServiceException {
		ObjectWriter viewWriter = CommonUtils.getObjectWriter(mapper);
		return ResponseEntity.ok(viewWriter.writeValueAsString(postService.findAllPost(false)));
	}

	@GetMapping(value = { URIConstant.ADMIN_POSTS })
	public ResponseEntity<?> getManagePosts(HttpServletRequest request) throws JsonProcessingException, ServiceException {
		ObjectWriter viewWriter = CommonUtils.getObjectWriter(mapper);
		return ResponseEntity.ok(viewWriter.writeValueAsString(postService.findAllPost(true)));
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = { URIConstant.ADMIN_POSTS })
	public ResponseEntity<?> createPost(@Valid @RequestBody PostRequest post) throws ServiceException {
		return ResponseEntity.ok(postService.createPost(post));
	}

	@PreAuthorize("isAuthenticated()")
	@PutMapping(value = { URIConstant.ADMIN_UPDATE_POSTS })
	public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody PostRequest post) throws ServiceException {
		postService.updatePost(id, post);
		return ResponseEntity.ok(null);
	}
	
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping(value = { URIConstant.ADMIN_DELETE_POSTS })
	public ResponseEntity<?> deletePost(@PathVariable Long id, HttpServletRequest request) throws ServiceException {
		postService.deletePost(id);
		return ResponseEntity.ok(null);
	}
}
