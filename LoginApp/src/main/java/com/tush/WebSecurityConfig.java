package com.tush;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private DataSource datasource;
	
	@Bean
	public UserDetailsService userdetails(){

		return new CustomUserDetailsService();
		
	}
	
	@Bean 
	public BCryptPasswordEncoder passEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean 
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authPro = new DaoAuthenticationProvider();
		authPro.setUserDetailsService(userdetails());
		authPro.setPasswordEncoder(passEncoder());
		return authPro;
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/home").authenticated()
		.anyRequest().permitAll()
		.and()
		.formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/home")
		.permitAll()
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");

	}
	

}
