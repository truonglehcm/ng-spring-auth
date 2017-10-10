package demo.spring.angular.auth.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.spring.angular.auth.persistence.service.IUserService;
import demo.spring.angular.auth.utils.TokenUtil;
import demo.spring.angular.auth.utils.URIConstant;
import demo.spring.angular.auth.web.exception.ServiceException;
import demo.spring.angular.auth.web.response.JwtUserResponse;
import io.swagger.annotations.Api;

@Api
@RestController
public class UserController {

	@Autowired
	private TokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private IUserService userService;

	@GetMapping(value = URIConstant.USER_PROFILE)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getAuthenticatedUser(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		String username = jwtTokenUtil.getUsernameFromToken(token);
		JwtUserResponse user = (JwtUserResponse) userDetailsService.loadUserByUsername(username);
		return ResponseEntity.ok(user);
	}

	@PostMapping(value = URIConstant.RESET_PASSWORD)
	public ResponseEntity<?> resetPassword(HttpServletRequest httpRequest, Device device,
			@Email @RequestParam(value = "email", required = true) String email) throws ServiceException {
		userService.resetPassword(email, httpRequest, device);
		return ResponseEntity.ok().body(null);
	}

	@PostMapping(value = URIConstant.RESET_PASSWORD_CONFIRM)
	public ResponseEntity<?> resetNewPassword(@RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "newPassword", required = true) String newPassword) throws ServiceException {
		userService.resetNewPassword(token, newPassword);
		return ResponseEntity.ok().body(null);
	}

}
