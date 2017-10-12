package demo.spring.angular.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import demo.spring.angular.auth.persistence.enums.AuthorityName;
import demo.spring.angular.auth.web.filter.AuthEntryPoint;
import demo.spring.angular.auth.web.filter.AuthFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthEntryPoint jwtAuthEndPoint;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthFilter jwtAuthFilter() {
		return new AuthFilter();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		String[] menthodGetPatterns = new String[] { 
				"/", 
				"/posts/**", 
				"/v2/**", 
				"/*.html", 
				"/favicon.ico", 
				"/**/*.html",
				"/**/*.css", 
				"/**/*.js", 
				"/libs/**/*", 
				"/img/*"
		};
		
		String[] permitAllPatterns = new String[] { 
				"/auth/**", 
				"/reset/password/**", 
				"/reset/password/confirm/**"
		};
		
		String[] adminRequiredPattern = new String[] {
				"/management/dashboard",
				"/management/users",
				"/management/tags",
				"/management/tags",
				"/management/config"
		};
		
		String[] authRequiredPattern = new String[] {
				"/management/posts/**", 
				"/management/profile",
				"/user/profile/**",
				"/user/change-password/**"
		};

		httpSecurity
				// we don't need CSRF because our token is invulnerable
				.csrf().disable()

				.exceptionHandling().authenticationEntryPoint(jwtAuthEndPoint).and()

				// don't create session
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				
				.authorizeRequests()
				
				// cors request config
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

				// allow anonymous resource requests
				.antMatchers(HttpMethod.GET, menthodGetPatterns).permitAll()
				.antMatchers(permitAllPatterns).permitAll()
				.antMatchers(authRequiredPattern).hasAnyAuthority(AuthorityName.ROLE_ADMIN.name(), AuthorityName.ROLE_USER.name())
				.antMatchers(adminRequiredPattern).hasAuthority(AuthorityName.ROLE_ADMIN.name())
				.anyRequest().authenticated();

		// Custom JWT based security filter
		httpSecurity
				.addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);

		// disable page caching
		httpSecurity
				.headers().cacheControl();

	}
}
