package demo.spring.angular.auth.web.exception;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;

public interface ExceptionAttributes {

	/**
	 * Returns a {@link Map} of exception attributes. The Map may be used to display an error page or serialized into a {@link ResponseBody}.
	 * 
	 * @param exception
	 *            The Exception reported.
	 * @param httpRequest
	 *            The HttpServletRequest in which the Exception occurred.
	 * @param httpStatus
	 *            The HttpStatus value that will be used in the {@link HttpServletResponse}.
	 * @return A Map of exception attributes.
	 */
	Map<String, Object> getExceptionAttributes(Exception exception, HttpServletRequest httpRequest, HttpStatus httpStatus);

}
