package demo.spring.angular.auth.web.response;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtUserResponse implements UserDetails {
	
	@JsonIgnore
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	private Long id;
	private String username;
	private String firstname;
	private String lastname;
	@JsonIgnore
	private String password;
	private String email;
	private Collection<? extends GrantedAuthority> authorities;
	private boolean enabled;
	@JsonIgnore
	private Date lastPasswordReset;

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	public static class JwtUserBuilder {
		private Long id;
		private String username;
		private String firstname;
		private String lastname;
		private String password;
		private String email;
		private Collection<? extends GrantedAuthority> authorities;
		private boolean enabled;
		private Date lastPasswordReset;

        public JwtUserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public JwtUserBuilder username(String username) {
            this.username = username;
            return this;
        }
        
        public JwtUserBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }
        
        public JwtUserBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public JwtUserBuilder password(String password) {
            this.password = password;
            return this;
        }
        
        public JwtUserBuilder email(String email) {
            this.email = email;
            return this;
        }
        
        public JwtUserBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = authorities;
            return this;
        }
        
        public JwtUserBuilder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }
        
        public JwtUserBuilder lastPasswordReset(Date lastPasswordReset) {
            this.lastPasswordReset = lastPasswordReset;
            return this;
        }
        
        public JwtUserResponse build() {
            return new JwtUserResponse(this);
        }

    }

    private JwtUserResponse(JwtUserBuilder builder) {
    	this.id = builder.id;
		this.username = builder.username;
		this.firstname = builder.firstname;
		this.lastname = builder.lastname;
		this.email = builder.email;
		this.password = builder.password;
		this.authorities = builder.authorities;
		this.enabled = builder.enabled;
		this.lastPasswordReset = builder.lastPasswordReset;
    }

}
