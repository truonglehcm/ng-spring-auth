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

import demo.spring.angular.auth.persistence.service.IUserService;
import demo.spring.angular.auth.utils.CommonUtils;
import demo.spring.angular.auth.utils.URIConstant;
import demo.spring.angular.auth.web.exception.ServiceException;
import demo.spring.angular.auth.web.request.UserRequest;
import demo.spring.angular.auth.web.request.UserUpdateRequest;
import io.swagger.annotations.Api;

@Api
@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
	
	@Autowired
    private ObjectMapper mapper;

    @Autowired
    private IUserService userService;
	
	@GetMapping(value = URIConstant.ADMIN_USERS)
    public ResponseEntity<?> getUsers(HttpServletRequest request) throws JsonProcessingException, ServiceException {
		ObjectWriter viewWriter = CommonUtils.getObjectWriter(mapper);
        return ResponseEntity.ok(viewWriter.writeValueAsString(userService.findAllUser()));
    }
	
	@PostMapping(value = URIConstant.ADMIN_USERS)
	public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest) throws ServiceException, JsonProcessingException {
		ObjectWriter viewWriter = CommonUtils.getObjectWriter(mapper);
		return ResponseEntity.ok(viewWriter.writeValueAsString(userService.createUser(userRequest)));
	}
	
	@PutMapping(value = URIConstant.ADMIN_USERS)
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserUpdateRequest updateUserRequest) throws ServiceException {
		userService.updateUser(updateUserRequest);
		return ResponseEntity.ok().body(null);
	}
	
    @DeleteMapping(value = {URIConstant.ADMIN_DELETE_USERS})
    public ResponseEntity<?> deleteUser(@PathVariable Long id) throws ServiceException {
		userService.deleteById(id);
    	return ResponseEntity.ok(null);
    }
}
