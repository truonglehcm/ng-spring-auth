package demo.spring.angular.auth.web.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import demo.spring.angular.auth.web.validation.EmailValid;
import demo.spring.angular.auth.web.validation.PasswordMatches;
import demo.spring.angular.auth.web.validation.PasswordValid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@PasswordMatches
public class SignupRequest {
	
	@NotNull
	@Size(min = 4, max = 50)
	private String firstName;
	
	@NotNull
	@Size(min = 4, max = 50)
	private String lastName;
	
	@NotNull
	@EmailValid
	@Size(min = 4, max = 50)
	private String email;
	
	@PasswordValid
	@NotNull
	@Size(min = 8, max = 50)
	private String password;
	
	@NotNull
	private String matchingpassword;
	
	@NotNull
	private String recaptcha;
}