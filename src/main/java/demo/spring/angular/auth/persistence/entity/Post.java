package demo.spring.angular.auth.persistence.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post extends BaseEntity {

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "logo_url", nullable = true)
	private String logoUrl;

	@Column(name = "create_at", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;

	@Column(name = "visible", nullable = false)
	private boolean visible;

	@Column(name = "visited")
	private Integer visited;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private Set<Tag> tags = new HashSet<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "create_by", nullable = false)
	private User user;

	public Post() {
		this.createAt = new Date();
		this.visited = 0;
	}

	public void addTag(Tag tag) {
		tags.add(tag);
		tag.getPosts().add(this);
	}

	public void removeTag(Tag tag) {
		tags.remove(tag);
		tag.getPosts().remove(this);
	}
}
