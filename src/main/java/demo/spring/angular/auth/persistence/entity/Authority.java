package demo.spring.angular.auth.persistence.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

import demo.spring.angular.auth.persistence.enums.AuthorityName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "authority")
@Getter
@Setter
@NoArgsConstructor
public class Authority extends BaseEntity {
	
	@NaturalId
	@NotNull
	@Column(name = "name", length = 50)
	@Enumerated(EnumType.STRING)
	private AuthorityName name;

	@ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
	private Set<User> users = new HashSet<>();
}