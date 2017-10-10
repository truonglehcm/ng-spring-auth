package demo.spring.angular.auth.web.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TagRequest {
	private Long id;
	@NotNull
	@Size(min = 3, max = 255)
	private String name;
	@NotNull
	private Boolean visible;
}
