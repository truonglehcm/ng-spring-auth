package demo.spring.angular.auth.persistence.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.NaturalId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tag")
@Getter
@Setter
@NoArgsConstructor
public class Tag extends BaseEntity {
	
	@NaturalId(mutable = true)
	private String name;

	@Column(name = "visible", nullable = false)
	private boolean visible;

	@ManyToMany(mappedBy = "tags")
	private Set<Post> posts = new HashSet<>();
}
