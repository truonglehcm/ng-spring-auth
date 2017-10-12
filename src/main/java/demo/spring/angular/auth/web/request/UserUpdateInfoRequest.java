package demo.spring.angular.auth.web.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateInfoRequest {
	@NotNull
	@Size(min = 4, max = 50)
	private String username;
	@NotNull
	@Size(min = 4, max = 50)
    private String firstName;
	@NotNull
	@Size(min = 4, max = 50)
    private String lastName;
}