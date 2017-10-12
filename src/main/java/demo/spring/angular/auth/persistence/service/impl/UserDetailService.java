package demo.spring.angular.auth.persistence.service.impl;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import demo.spring.angular.auth.persistence.entity.User;
import demo.spring.angular.auth.persistence.service.IUserService;
import demo.spring.angular.auth.web.exception.ServiceException;
import demo.spring.angular.auth.web.exception.UserNotEnabledException;
import demo.spring.angular.auth.web.response.JwtUserResponse;

@Service
@Transactional
public class UserDetailService implements UserDetailsService {

	@Autowired
	private IUserService userService;

	public JwtUserResponse loadUserByUsername(String username) {
		
		try {
			User user = userService.findByUsername(username);
			
			// Not found...
			if (Objects.isNull(user)) {
				throw new UsernameNotFoundException("User " + username + " not found.");
			}
			
			// Not enable...
			if (!user.isEnabled() || user.isExpired() || user.isLocked()) {
				throw new UserNotEnabledException("User " + username + " was not enabled");
			}
			
			// No Roles assigned to user...
			if (CollectionUtils.isEmpty(user.getAuthorities())) {
				throw new UsernameNotFoundException("User not authorized.");
			}

			Collection<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
					.map(a -> new SimpleGrantedAuthority(a.getName().name())).collect(Collectors.toList());

			return new JwtUserResponse.JwtUserBuilder()
					.id(user.getId())
					.userName(user.getUserName())
					.firstName(user.getFirstName())
					.lastName(user.getLastName())
					.email(user.getEmail())
					.password(user.getPassword())
					.authorities(grantedAuthorities)
					.enabled(user.isEnabled())
					.lastPasswordReset(user.getLastPasswordReset())
					.build();
			
		} catch (ServiceException e) {
			throw new UsernameNotFoundException("User " + username + " not found.");
		}

	}
}
