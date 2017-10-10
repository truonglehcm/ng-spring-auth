package demo.spring.angular.auth.web.request;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRequest {
	private Long id;
	@NotNull
	@Size(min = 2, max = 255)
	private String title;
	@NotNull
	@Size(min = 2, max = 1000)
	private String description;
	@NotNull
	private String content;
	private String logoUrl;
	private String createBy;
	private Date createAt;
}
