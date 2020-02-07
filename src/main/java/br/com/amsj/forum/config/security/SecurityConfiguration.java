package br.com.amsj.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.amsj.forum.config.security.filter.AuthorizationBasicFilter;
import br.com.amsj.forum.config.security.filter.AuthorizationTokenFilter;
import br.com.amsj.forum.repository.UsuarioRepository;

@EnableWebSecurity//(debug = true) // Only for debug mode
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TokenService tokenService;
	
	private static String ACTUATOR_ROLE = "ACTUATOR"; //TODO CREATE A ENUM AND PERSIST
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	// Configuration for authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	// Authorization configuration
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/topicos").permitAll()
		.antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
		.antMatchers(HttpMethod.POST, "/oauth/*").permitAll()
		.antMatchers(HttpMethod.GET, "/actuator/**").hasAnyAuthority(ACTUATOR_ROLE) // TODO
		.antMatchers("/h2-console/**").permitAll() // FOR H2-CONSOLE
		.anyRequest().authenticated().and()
		//formLogin(); // for session
		.csrf().disable()
		.headers().frameOptions().disable().and() // FOR H2-CONSOLE
		.addFilterBefore(new AuthorizationTokenFilter(usuarioRepository, tokenService), UsernamePasswordAuthenticationFilter.class) // Include the new Filter before the UsernamePasswordAuthenticationFilter 
		.addFilterBefore(new AuthorizationBasicFilter(usuarioRepository), UsernamePasswordAuthenticationFilter.class) // Include the new Filter before the UsernamePasswordAuthenticationFilter
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}
	
	// Configuration for static resources
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
		web.ignoring().antMatchers("/*.css");
		web.ignoring().antMatchers("/*.js");
	}
	
	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("test"));
	}

}
