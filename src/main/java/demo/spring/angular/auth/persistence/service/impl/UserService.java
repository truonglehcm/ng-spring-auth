package demo.spring.angular.auth.persistence.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mobile.device.Device;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import demo.spring.angular.auth.event.OnRegistrationCompleteEvent;
import demo.spring.angular.auth.event.OnResetPasswordCompleteEvent;
import demo.spring.angular.auth.persistence.entity.Authority;
import demo.spring.angular.auth.persistence.entity.PasswordResetToken;
import demo.spring.angular.auth.persistence.entity.User;
import demo.spring.angular.auth.persistence.entity.VerificationToken;
import demo.spring.angular.auth.persistence.enums.AuthorityName;
import demo.spring.angular.auth.persistence.repository.AuthorityRepository;
import demo.spring.angular.auth.persistence.repository.PasswordResetTokenRepository;
import demo.spring.angular.auth.persistence.repository.UserRepository;
import demo.spring.angular.auth.persistence.repository.VerificationTokenRepository;
import demo.spring.angular.auth.persistence.service.IUserService;
import demo.spring.angular.auth.utils.AuthoritiesConstants;
import demo.spring.angular.auth.utils.CommonUtils;
import demo.spring.angular.auth.utils.MessageConstant;
import demo.spring.angular.auth.utils.SecurityUtils;
import demo.spring.angular.auth.utils.SystemConstant;
import demo.spring.angular.auth.web.exception.EmailExistsException;
import demo.spring.angular.auth.web.exception.ServiceException;
import demo.spring.angular.auth.web.exception.TokenNotFoundException;
import demo.spring.angular.auth.web.exception.UserNotFoundException;
import demo.spring.angular.auth.web.request.SignupRequest;
import demo.spring.angular.auth.web.request.UserRequest;
import demo.spring.angular.auth.web.request.UserUpdateRequest;
import demo.spring.angular.auth.web.response.UserResponse;

@Service
@Transactional
public class UserService extends GenericService<User, Long> implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Override
	public User findByUsername(String username) throws ServiceException {
		try {
			return userRepository.findByUserName(username);
		} catch (Exception e) {
			throw new ServiceException("PostService --> findByUsername()", e);
		}
	}

	@Override
	public User findUserByEmail(String email) throws ServiceException {
		try {
			return userRepository.findByEmail(email);
		} catch (Exception e) {
			throw new ServiceException("PostService --> findUserByEmail()", e);
		}
	}

	@Override
	public void createVerificationToken(User user, String token) throws ServiceException {
		try {
	        verificationTokenRepository.save(new VerificationToken(user, token));
		} catch (Exception e) {
			throw new ServiceException("PostService --> createVerificationToken()", e);
		}
	}

	@Override
	public void createPasswordResetToken(User user, String token) throws ServiceException {
		try {
			PasswordResetToken myToken = passwordResetTokenRepository.findOne(user.getId());
			if (Objects.nonNull(myToken)) {
				myToken.setToken(token);
				myToken.setExpiry(CommonUtils.calculateExpiryDate(SystemConstant.EXPIRY_DATE));
			} else {
				myToken = new PasswordResetToken(user, token);
				passwordResetTokenRepository.save(myToken);
			}
		} catch (Exception e) {
			throw new ServiceException("PostService --> createPasswordResetToken()", e);
		}
	}
	
	@Override
	public void verificationToken(String token) throws ServiceException {
		try {
			VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
			if (Objects.isNull(verificationToken)) {
				throw new TokenNotFoundException("Token is not found");
			}
			verificationToken.getUser().setEnabled(true);
			verificationTokenRepository.delete(verificationToken);
		} catch (Exception e) {
			throw new ServiceException("PostService --> verificationToken()", e);
		}
	}

	@Override
	public UserResponse createUser(UserRequest userRequest) throws ServiceException {
		try {
			// 1. check if user exist
			User existingUser = userRepository.findByEmail(userRequest.getEmail());
			if (Objects.nonNull(existingUser)) {
				String messageError = messageSource.getMessage(MessageConstant.EMAIL_EXIST, null, Locale.US);
				throw new EmailExistsException(messageError);
			}

			// 2. set new user
			User user = new User();
			user.setUserName(userRequest.getUserName());
			user.setEmail(userRequest.getEmail());
			user.setFirstName(userRequest.getFirstName());
			user.setLastName(userRequest.getLastName());
			user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
			user.setLastPasswordReset(new Date());
			user.setEnabled(true);
			user.setExpired(false);
			user.setLocked(false);

			// 3. add authority
			Authority authority = authorityRepository.findByName(AuthorityName.ROLE_ADMIN);
			if (Objects.nonNull(authority)) {
				Set<Authority> authorities = new HashSet<>();
				authorities.add(authority);
				user.setAuthorities(authorities);
			}

			// 4. save user
			userRepository.save(user);
			return mapper.map(user, UserResponse.class);
		} catch (Exception e) {
			throw new ServiceException("PostService --> createUser()", e);
		}
	}

	@Override
	public void updateUser(UserUpdateRequest updateUserRequest) throws ServiceException {
		try {
			// 1. check if user exist
			User existingUser = userRepository.findOne(updateUserRequest.getId());
			if (Objects.isNull(existingUser)) {
				throw new UserNotFoundException("User is not exist");
			}

			// 2. set new user
			existingUser.setUserName(updateUserRequest.getUserName());
			existingUser.setFirstName(updateUserRequest.getFirstName());
			existingUser.setLastName(updateUserRequest.getLastName());
			existingUser.setEmail(updateUserRequest.getEmail());
			existingUser.setEnabled(updateUserRequest.getEnabled());
			existingUser.setExpired(updateUserRequest.getExpired());
			existingUser.setLocked(updateUserRequest.getLocked());

			// 4. save user
			userRepository.save(existingUser);
		} catch (Exception e) {
			throw new ServiceException("PostService --> updateUser()", e);
		}
	}

	@Override
	public void deleteUser(Long id) throws ServiceException {
		try {
			if(!SecurityUtils.isAuthenticated() || !SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {
				throw new AccessDeniedException("Access deniel");
	    	} 
	    	userRepository.delete(id);
		} catch (Exception e) {
			throw new ServiceException("PostService --> deleteUser()", e);
		}
	}

	@Override
	public void signUp(SignupRequest signUpRequest, HttpServletRequest httpRequest, Device device) throws ServiceException {
		try {
			// 1. check if user exist
			User existingUser = userRepository.findByEmail(signUpRequest.getEmail());
			if (Objects.nonNull(existingUser)) {
				String messageError = messageSource.getMessage(MessageConstant.EMAIL_EXIST, null, Locale.US);
				throw new EmailExistsException(messageError);
			}

			// 2. set new user
			User user = new User();
			user.setUserName(signUpRequest.getEmail());
			user.setEmail(signUpRequest.getEmail());
			user.setFirstName(signUpRequest.getFirstName());
			user.setLastName(signUpRequest.getLastName());
			user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
			user.setEnabled(false);
			user.setLocked(false);
			user.setExpired(false);
			user.setLastPasswordReset(new Date());
			
			// 3. add authority
			Authority authority = authorityRepository.findByName(AuthorityName.ROLE_USER);
			if (Objects.nonNull(authority)) {
				Set<Authority> authorities = new HashSet<>();
				authorities.add(authority);
				user.setAuthorities(authorities);
			}

			// 4. save user
			userRepository.save(user);

			// 5. send email verification
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, httpRequest, device));
		} catch (Exception e) {
			throw new ServiceException("PostService --> signUp()", e);
		}
	}

	@Override
	public void resetPassword(String email, HttpServletRequest httpRequest, Device device) throws ServiceException {
		try {
			// 1. check if user exist
			User existingUser = userRepository.findByEmail(email);
			if (Objects.isNull(existingUser)) {
				String messageError = messageSource.getMessage(MessageConstant.EMAIL_EXIST, null, httpRequest.getLocale());
				throw new EmailExistsException(messageError);
			}

			// 2. send email verification
			eventPublisher.publishEvent(new OnResetPasswordCompleteEvent(existingUser, httpRequest, device));
		} catch (Exception e) {
			throw new ServiceException("PostService --> resetPassword()", e);
		}
	}

	@Override
	public void resetNewPassword(String token, String newPassword) throws ServiceException {
		try {
			// check token expired
			PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
			if (Objects.isNull(passwordResetToken) || passwordResetToken.isExpired()) {
				throw new TokenNotFoundException("Token not found");
			}
			
			User user = passwordResetToken.getUser();
			user.setPassword(passwordEncoder.encode(newPassword));
			user.setLastPasswordReset(new Date());
			
			passwordResetTokenRepository.delete(passwordResetToken);
		} catch (Exception e) {
			throw new ServiceException("PostService --> resetNewPassword()", e);
		}
	}

	@Override
	public List<UserResponse> findAllUser() throws ServiceException {
		return CollectionUtils.emptyIfNull(userRepository.findAll())
				.stream()
				.map(u -> mapper.map(u, UserResponse.class))
				.collect(Collectors.toList());
	}
}
