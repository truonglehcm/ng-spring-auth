package demo.spring.angular.auth.web.response;

import com.fasterxml.jackson.annotation.JsonView;

import demo.spring.angular.auth.web.views.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TagResponse {
	@JsonView(Views.Anonymous.class)
	private Long id;

	@JsonView(Views.Anonymous.class)
	private String name;
	
	@JsonView(Views.Admin.class)
	private boolean visible;
}
