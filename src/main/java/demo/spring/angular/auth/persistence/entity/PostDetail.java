package demo.spring.angular.auth.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post_detail")
@Getter
@Setter
@NoArgsConstructor
public class PostDetail extends BaseEntity {

	@Column(name = "post_content", nullable = false)
	private String content;

	@MapsId
	@OneToOne(fetch = FetchType.LAZY)
	private Post post;

	public PostDetail(String content) {
		this.content = content;
	}

	public PostDetail(String content, Post post) {
		this.content = content;
		this.post = post;
	}
}
