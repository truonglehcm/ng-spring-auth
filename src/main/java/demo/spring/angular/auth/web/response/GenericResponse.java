package demo.spring.angular.auth.web.response;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GenericResponse {
	private String message;
	private String error;

	public GenericResponse(final String message) {
		this.message = message;
	}

	public GenericResponse(final String message, final String error) {
		this.message = message;
		this.error = error;
	}

	public GenericResponse(final List<FieldError> fieldErrors, final List<ObjectError> globalErrors) {
		final ObjectMapper mapper = new ObjectMapper();
		try {
			
			String multiFieldErrors = CollectionUtils.emptyIfNull(fieldErrors)
									.stream()
									.map(e -> e.getDefaultMessage())
									.collect(Collectors.joining(","));
			
			String multiGlobalErrors = CollectionUtils.emptyIfNull(globalErrors)
									.stream()
									.map(e -> e.getDefaultMessage())
									.collect(Collectors.joining(","));
			
			this.message = mapper.writeValueAsString(multiFieldErrors);
			this.error = mapper.writeValueAsString(multiGlobalErrors);
		} catch (final JsonProcessingException e) {
			this.message = "";
			this.error = "";
		}
	}
}
