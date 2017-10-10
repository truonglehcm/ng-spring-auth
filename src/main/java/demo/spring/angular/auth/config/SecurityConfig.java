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

import demo.spring.angular.auth.utils.AuthoritiesConstants;
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

		String[] patterns = new String[] { "/", "/posts/**", "/v2/**", "/*.html", "/favicon.ico", "/**/*.html",
				"/**/*.css", "/**/*.js", "/libs/**/*", "/img/*" };

		httpSecurity
				// we don't need CSRF because our token is invulnerable
				.csrf().disable()

				.exceptionHandling().authenticationEntryPoint(jwtAuthEndPoint).and()

				// don't create session
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

				.authorizeRequests()
				// .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

				// allow anonymous resource requests
				.antMatchers(HttpMethod.GET, patterns).permitAll().antMatchers("/auth/**").permitAll()
				.antMatchers("/reset/password/**").permitAll().antMatchers("/reset/password/confirm/**").permitAll()
				.antMatchers("/management/posts/**", "/management/profile").hasAnyAuthority("ROLE_USER, ROLE_ADMIN")
				.antMatchers("/management/dashboard").hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/management/users")
				.hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/management/tags").hasAuthority(AuthoritiesConstants.ADMIN)
				.antMatchers("/management/config").hasAuthority(AuthoritiesConstants.ADMIN).anyRequest().authenticated();

		// Custom JWT based security filter
		httpSecurity.addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);

		// disable page caching
		httpSecurity.headers().cacheControl();

	}
}
