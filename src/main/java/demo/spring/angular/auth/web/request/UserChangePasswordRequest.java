package demo.spring.angular.auth.web.request;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import demo.spring.angular.auth.web.validation.PasswordValid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserChangePasswordRequest {
	@NotBlank
	@Size(min = 4, max = 50)
	private String username;
	@NotBlank
	@Size(min = 4, max = 50)
    private String oldPassword;
	@PasswordValid
	@NotBlank
	@Size(min = 8, max = 50)
	private String newPassword;
	@NotBlank
	private String newPasswordConfirm;
}