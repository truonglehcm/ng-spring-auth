package demo.spring.angular.auth.web.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;

import demo.spring.angular.auth.web.views.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {
	
	@JsonView(Views.Anonymous.class)
	private Long id;

	@JsonView(Views.Anonymous.class)
	private String title;

	@JsonView(Views.Anonymous.class)
	private String description;

	@JsonView(Views.Anonymous.class)
	private String content;

	@JsonView(Views.Anonymous.class)
	private String logoUrl;

	@JsonView(Views.Anonymous.class)
	private Date createAt;

	@JsonView(Views.Anonymous.class)
	private String createBy;

	@JsonView(Views.User.class)
	private boolean visible;

	@JsonView(Views.Anonymous.class)
	private String visited;
}
