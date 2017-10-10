package demo.spring.angular.auth.web.response;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthorityResponse {
	private Long id;
	private String name;
	private List<UserResponse> users;
}