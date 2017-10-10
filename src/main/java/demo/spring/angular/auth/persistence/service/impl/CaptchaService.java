package demo.spring.angular.auth.persistence.service.impl;

import java.net.URI;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import demo.spring.angular.auth.persistence.service.ICaptchaService;
import demo.spring.angular.auth.utils.SystemConstant;
import demo.spring.angular.auth.utils.URIConstant;
import demo.spring.angular.auth.web.exception.ReCaptchaInvalidException;
import demo.spring.angular.auth.web.exception.ReCaptchaUnavailableException;
import demo.spring.angular.auth.web.response.GoogleResponse;

@Service
@Transactional
public class CaptchaService implements ICaptchaService {

	@Value("${google.recaptcha.secret-key}")
	private String secretKey;
	
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private RestOperations restTemplate;

	@Override
	public void processResponse(String response) {

		boolean responseSanityCheck = StringUtils.hasLength(response) && SystemConstant.RESPONSE_PATTERN.matcher(response).matches();
		if (!responseSanityCheck) {
			throw new ReCaptchaInvalidException("Response contains invalid characters");
		}

		URI verifyUri = URI.create(String.format(URIConstant.RECAPTCHA_SITEVERIFY, secretKey, response, getClientIP()));
		try {
			GoogleResponse googleResponse = restTemplate.getForObject(verifyUri, GoogleResponse.class);

			if (!googleResponse.isSuccess()) {
				throw new ReCaptchaInvalidException("reCaptcha was not successfully validated");
			}
		} catch (RestClientException rce) {
			throw new ReCaptchaUnavailableException("Registration unavailable at this time.  Please try again later.", rce);
		}
	}

	private String getClientIP() {
		final String xfHeader = request.getHeader(SystemConstant.X_FORWARDED_FOR);
		if (Objects.isNull(xfHeader)) {
			return request.getRemoteAddr();
		}
		return xfHeader.split(",")[0];
	}
}
