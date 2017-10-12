package demo.spring.angular.auth.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import demo.spring.angular.auth.persistence.enums.AuthorityName;
import demo.spring.angular.auth.web.views.Views;

public final class CommonUtils {
	
	private CommonUtils() {
		
	}
	
	public static String getBaseUrl(HttpServletRequest httpRequest) {
		
		if (Objects.isNull(httpRequest)) {
			return StringUtils.EMPTY;
		}

		try {
			return httpRequest.getScheme() + "://" + httpRequest.getServerName() + ":" + httpRequest.getServerPort() + httpRequest.getContextPath();
		} catch (Exception e) {
			return StringUtils.EMPTY;
		}
	}

	public static Date calculateExpiryDate(final int expiryTimeInMinutes) {
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new Date().getTime());
		cal.add(Calendar.MINUTE, expiryTimeInMinutes);
		return new Date(cal.getTime().getTime());
	}

	public static ObjectWriter getObjectWriter(ObjectMapper mapper) {
		
		// ADMIN
		if (SecurityUtils.isCurrentUserInRole(AuthorityName.ROLE_ADMIN.name())) {
			return mapper.writerWithView(Views.Admin.class);
		} 

		// USER
		if (SecurityUtils.isCurrentUserInRole(AuthorityName.ROLE_USER.name())) {
			return mapper.writerWithView(Views.User.class);
		}
		
		// ANONYMOUS
		return mapper.writerWithView(Views.Anonymous.class);
	}

}
