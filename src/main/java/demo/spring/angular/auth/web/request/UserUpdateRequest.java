package demo.spring.angular.auth.web.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import demo.spring.angular.auth.web.validation.EmailValid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateRequest {
	private Long id;
	@NotNull
	@Size(min = 4, max = 50)
	private String userName;
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
    @NotNull
	private Boolean enabled;
    @NotNull
	private Boolean expired;
    @NotNull
	private Boolean locked;
}