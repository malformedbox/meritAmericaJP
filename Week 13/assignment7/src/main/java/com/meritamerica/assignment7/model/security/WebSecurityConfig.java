package com.meritamerica.assignment7.model.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.meritamerica.assignment7.model.security.service.UserDetailsServiceImpl;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	@Autowired
	UserDetailsServiceImpl userDetailService;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// + from users <- can be used to rename the column
		//auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
		
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select username,password,enabled "
					+ "from users "
					+ "where username = ?")
			.authoritiesByUsernameQuery("select username, authority "
					+ "from authorities "
					+ "where username = ?");
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() { return NoOpPasswordEncoder.getInstance();}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
				.antMatchers("/admin").hasRole("ADMIN")
				.antMatchers("/user").hasAnyRole("ADMIN", "USER")
				.antMatchers("/").permitAll()
				.and().formLogin();
	}
}