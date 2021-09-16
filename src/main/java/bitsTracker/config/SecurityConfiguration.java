package bitsTracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import bitsTracker.service.interfaces.BitUserService;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BitUserService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() //
				.antMatchers("/resources/**").permitAll()//
				.antMatchers("/registration**", "/saveBitUser").permitAll()//
				.antMatchers("/admin").hasRole("ADMIN")//
				.antMatchers("/user").hasAnyRole("ADMIN", "USER")//
				.anyRequest().authenticated()//
				.and().formLogin().loginPage("/login").permitAll()//
				.and().logout()//
				.invalidateHttpSession(true)//
				.clearAuthentication(true)//
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))//
				.logoutSuccessUrl("/login?logout").permitAll();

	}

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userDetailsService);
		auth.setPasswordEncoder(getPasswordEncoder());
		return auth;

	}
}
