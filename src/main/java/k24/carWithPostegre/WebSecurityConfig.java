package k24.carWithPostegre;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import k24.carWithPostegre.service.UserDetailServiceImpl;

@Configuration
@EnableMethodSecurity(securedEnabled = true) // SB 3.0
public class WebSecurityConfig {
	@Autowired
	private UserDetailServiceImpl userDetailsService;

	private static final AntPathRequestMatcher[] WHITE_LIST_URLS = {
			new AntPathRequestMatcher("/h2-console/**"),
			new AntPathRequestMatcher("/api/**"),
			new AntPathRequestMatcher("/cars/**") };

	private static final AntPathRequestMatcher[] ADMIN_LIST_URLS = {
			new AntPathRequestMatcher("/admin/**"),
			new AntPathRequestMatcher("/owner/**") };

	// secure configurations with lambda
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorizeRequest -> authorizeRequest
				.requestMatchers(antMatcher("/css/**")).permitAll()
				// .requestMatchers(antMatcher("/api/**")).permitAll()
				.requestMatchers(antMatcher("/vaatteet/**")).permitAll()
				.requestMatchers(WHITE_LIST_URLS).permitAll().requestMatchers(ADMIN_LIST_URLS)
				.hasAuthority("ADMIN")
				.anyRequest().authenticated())
				.headers(headers -> headers
						.frameOptions(frameOptions -> frameOptions.disable())) // enable h2console
				.formLogin(formlogin -> formlogin
						.defaultSuccessUrl("/carlist", true).permitAll())
				.logout(logout -> logout.permitAll())
				.csrf(csrf -> csrf.disable()); // not for production, just for development
		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
