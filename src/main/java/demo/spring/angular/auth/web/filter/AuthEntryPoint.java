package demo.spring.angular.auth.web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException e) throws IOException, ServletException {
		httpServletResponse.setStatus(SC_FORBIDDEN);
		httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

		String message;
		if (Objects.nonNull(e.getCause())) {
			message = e.getCause().getMessage();
		} else {
			message = e.getMessage();
		}
		byte[] body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("error", message));
		httpServletResponse.getOutputStream().write(body);
	}
}
