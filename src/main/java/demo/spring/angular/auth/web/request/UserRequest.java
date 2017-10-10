package demo.spring.angular.auth.web.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import demo.spring.angular.auth.web.validation.EmailValid;
import demo.spring.angular.auth.web.validation.PasswordValid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {
	@NotNull
	@Size(min = 3, max = 50)
	private String userName;
	@NotNull
	@Size(min = 3, max = 50)
    private String firstName;
	@NotNull
	@Size(min = 3, max = 50)
    private String lastName;
	@NotNull
	@EmailValid
	@Size(min = 3, max = 50)
    private String email;
	@NotNull
	@PasswordValid
	@Size(min = 8, max = 50)
    private String password;
	@NotNull
	private Boolean enabled;
	@NotNull
	private Boolean expired;
	@NotNull
	private Boolean locked;
}