package demo.spring.angular.auth.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.spring.angular.auth.persistence.service.ICaptchaService;
import demo.spring.angular.auth.persistence.service.IUserService;
import demo.spring.angular.auth.utils.TokenUtil;
import demo.spring.angular.auth.utils.URIConstant;
import demo.spring.angular.auth.web.exception.ServiceException;
import demo.spring.angular.auth.web.request.AuthRequest;
import demo.spring.angular.auth.web.request.SignupRequest;
import demo.spring.angular.auth.web.response.AuthTokenResponse;
import demo.spring.angular.auth.web.response.JwtUserResponse;
import demo.spring.angular.auth.web.validation.TokenValid;
import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping(path = URIConstant.AUTH)
public class AuthController {

	@Autowired
	private TokenUtil jwtTokenUtil;

	@Autowired
	private IUserService userService;

	@Autowired
	private ICaptchaService captchaService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping(value = URIConstant.LOG_IN)
	public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthRequest authRequest, Device device) {

		// Perform the security
		final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetail = userDetailsService.loadUserByUsername(authRequest.getUsername());
		String token = jwtTokenUtil.generateToken(userDetail, device);

		// Return the token
		return ResponseEntity.ok().body(new AuthTokenResponse(token));
	}

	@GetMapping(value = URIConstant.LOG_IN)
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {

		// get token
		String token = request.getHeader("Authorization");
		String username = jwtTokenUtil.getUsernameFromToken(token);
		JwtUserResponse user = (JwtUserResponse) userDetailsService.loadUserByUsername(username);

		// check token
		boolean isRefresh = jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordReset());
		if (!isRefresh) {
			return ResponseEntity.badRequest().body(null);
		}

		// refresh token
		String refreshedToken = jwtTokenUtil.refreshToken(token);
		return ResponseEntity.ok().body(new AuthTokenResponse(refreshedToken));

	}

	@PostMapping(value = URIConstant.SIGN_UP)
	public ResponseEntity<?> signUp(@Valid @RequestBody SignupRequest signUpRequest, HttpServletRequest httpRequest, Device device) throws ServiceException {
		captchaService.processResponse(signUpRequest.getRecaptcha());
		userService.signUp(signUpRequest, httpRequest, device);
		return ResponseEntity.ok().body(null);
	}

	@PostMapping(value = URIConstant.SIGNUP_CONFIRM)
	public ResponseEntity<?> signUpConfirm(@Valid @TokenValid @PathVariable("token") String token) throws ServiceException {
		userService.verificationToken(token);
		return ResponseEntity.ok().body(null);
	}
}