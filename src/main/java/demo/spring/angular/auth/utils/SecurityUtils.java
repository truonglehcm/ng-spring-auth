package demo.spring.angular.auth.utils;

import java.util.Objects;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import demo.spring.angular.auth.persistence.enums.AuthorityName;

public final class SecurityUtils {
	
	private SecurityUtils() {
		
	}

	public static String getCurrentUserLogin() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		String userName = null;
		if (Objects.nonNull(authentication)) {
			if (authentication.getPrincipal() instanceof UserDetails) {
				UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
				userName = springSecurityUser.getUsername();
			} else if (authentication.getPrincipal() instanceof String) {
				userName = (String) authentication.getPrincipal();
			}
		}
		return userName;
	}

	public static boolean isAuthenticated() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		if (Objects.nonNull(authentication)) {
			return authentication.getAuthorities().stream().noneMatch(
					grantedAuthority -> grantedAuthority.getAuthority().equals(AuthorityName.ROLE_ANONYMOUS.name()));
		}
		return false;
	}

	public static boolean isCurrentUserInRole(String authority) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		if (Objects.nonNull(authentication)) {
			return authentication.getAuthorities().stream()
					.anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(authority));
		}
		return false;
	}
}
