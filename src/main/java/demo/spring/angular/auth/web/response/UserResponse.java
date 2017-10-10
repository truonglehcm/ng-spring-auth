package demo.spring.angular.auth.web.response;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonView;

import demo.spring.angular.auth.persistence.entity.Authority;
import demo.spring.angular.auth.persistence.entity.Post;
import demo.spring.angular.auth.web.views.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
	
	@JsonView(Views.Anonymous.class)
	private Long id;
	
	@JsonView(Views.Anonymous.class)
	private String userName;
	
	@JsonView(Views.Anonymous.class)
	private String firstName;
	
	@JsonView(Views.Anonymous.class)
	private String lastName;
	
	@JsonView(Views.Anonymous.class)
	private String email;
	
	@JsonView(Views.Admin.class)
	private boolean enabled;
	
	@JsonView(Views.Admin.class)
	private boolean expired;
	
	@JsonView(Views.Admin.class)
	private boolean locked;
	
	@JsonView(Views.Admin.class)
	private Date lastPasswordReset;
	
	@JsonView(Views.Admin.class)
	private Set<Authority> authorities;
	
	@JsonView(Views.Admin.class)
	private Set<Post> posts;
}
