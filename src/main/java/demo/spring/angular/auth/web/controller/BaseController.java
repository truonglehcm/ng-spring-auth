package demo.spring.angular.auth.web.controller;

import java.util.Map;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import demo.spring.angular.auth.web.exception.DefaultExceptionAttributes;
import demo.spring.angular.auth.web.exception.EmailExistsException;
import demo.spring.angular.auth.web.exception.ExceptionAttributes;
import demo.spring.angular.auth.web.exception.ReCaptchaInvalidException;
import demo.spring.angular.auth.web.exception.ReCaptchaUnavailableException;
import demo.spring.angular.auth.web.exception.ServiceException;
import demo.spring.angular.auth.web.exception.UserNotFoundException;
import demo.spring.angular.auth.web.response.GenericResponse;

@ControllerAdvice
public class BaseController extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messages;

	public BaseController() {
		super();
	}

	// 400
	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		BindingResult result = ex.getBindingResult();
		GenericResponse bodyOfResponse = new GenericResponse(result.getFieldErrors(), result.getGlobalErrors());
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	// 404
	@ExceptionHandler({ UserNotFoundException.class })
	public ResponseEntity<Object> handleUserNotFound(RuntimeException ex, WebRequest request) {
		GenericResponse bodyOfResponse = new GenericResponse(
				messages.getMessage("message.userNotFound", null, request.getLocale()), "UserNotFound");
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	// 500
	@ExceptionHandler({ MailAuthenticationException.class })
	public ResponseEntity<Object> handleMail(RuntimeException ex, WebRequest request) {
		GenericResponse bodyOfResponse = new GenericResponse(
				messages.getMessage("message.email.config.error", null, request.getLocale()), "MailError");
		return new ResponseEntity<Object>(bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult result = ex.getBindingResult();
		GenericResponse bodyOfResponse = new GenericResponse(result.getFieldErrors(), result.getGlobalErrors());
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ ReCaptchaInvalidException.class })
	public ResponseEntity<Object> handleReCaptchaInvalid(RuntimeException ex, WebRequest request) {
		GenericResponse bodyOfResponse = new GenericResponse(
				messages.getMessage("message.invalidReCaptcha", null, request.getLocale()), "InvalidReCaptcha");
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ EmailExistsException.class })
	public ResponseEntity<Object> handleEmailExistsException(RuntimeException ex, WebRequest request) {
		GenericResponse bodyOfResponse = new GenericResponse(
				messages.getMessage("message.emailExists", null, request.getLocale()), "EmailExistsException");
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ ReCaptchaUnavailableException.class })
	public ResponseEntity<Object> handleReCaptchaUnavailable(RuntimeException ex, WebRequest request) {
		GenericResponse bodyOfResponse = new GenericResponse(
				messages.getMessage("message.unavailableReCaptcha", null, request.getLocale()), "InvalidReCaptcha");
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,
				request);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleInternal(RuntimeException ex, WebRequest request) {
		GenericResponse bodyOfResponse = new GenericResponse(
				messages.getMessage("message.error", null, request.getLocale()), "InternalError");
		return new ResponseEntity<Object>(bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NoResultException.class)
	public ResponseEntity<Map<String, Object>> handleNoResultException(NoResultException noResultException,
			HttpServletRequest request) {
		ExceptionAttributes exceptionAttributes = new DefaultExceptionAttributes();
		Map<String, Object> responseBody = exceptionAttributes.getExceptionAttributes(noResultException, request, HttpStatus.NOT_FOUND);
		return new ResponseEntity<Map<String, Object>>(responseBody, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<Map<String, Object>> handleException(Exception exception, HttpServletRequest request) {
		ExceptionAttributes exceptionAttributes = new DefaultExceptionAttributes();
		Map<String, Object> responseBody = exceptionAttributes.getExceptionAttributes(exception, request, HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Map<String, Object>>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
