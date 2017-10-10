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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User extends BaseEntity {

	@NotNull
	@Column(name = "username", length = 50, unique = true)
	private String userName;

	@Column(name = "password", length = 100)
	@NotNull
	@Size(min = 4, max = 100)
	private String password;

	@Column(name = "firstname", length = 50)
	@NotNull
	@Size(min = 4, max = 50)
	private String firstName;

	@Column(name = "lastname", length = 50)
	@NotNull
	@Size(min = 4, max = 50)
	private String lastName;

	@Column(name = "email", length = 50)
	@NotNull
	@Size(min = 4, max = 50)
	private String email;

	@Column(name = "enabled")
	@NotNull
	private boolean enabled;

	@Column(name = "expired")
	@NotNull
	private boolean expired;

	@Column(name = "locked")
	@NotNull
	private boolean locked;

	@Column(name = "last_password_reset")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date lastPasswordReset;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "user_authority", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "authority_id") })
	private Set<Authority> authorities = new HashSet<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Post> posts = new HashSet<>();

	public String getFullName() {
		return this.lastName + " " + this.firstName;
	}

	public void addPost(Post post) {
		posts.add(post);
		post.setUser(this);
	}

	public void removePost(Post post) {
		posts.remove(post);
		post.setUser(null);
	}

	public void addAuthority(Authority auth) {
		authorities.add(auth);
		auth.getUsers().add(this);
	}

	public void removeAuthority(Authority auth) {
		authorities.remove(auth);
		auth.getUsers().remove(this);
	}
}