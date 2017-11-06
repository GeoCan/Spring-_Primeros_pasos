package com.plenumsoft.vuzee.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
	        .antMatchers("/resources/**").permitAll()
	        .antMatchers("/css/**").permitAll()
//	        .antMatchers("/tasks/**").hasRole("USUARIO")
	        .anyRequest().authenticated()
	        .and()
	    .formLogin()
	        .loginPage("/login")
	        .permitAll()
	        .and()
	    .logout()                                    
	        .permitAll(); 
		
		
		
//			.authorizeRequests()
//				.antMatchers("/css/**", "/index").permitAll()		
//				.antMatchers("/user/**").hasRole("USER")			
//				.and()
//			.formLogin()
//				.loginPage("/login").failureUrl("/login-error");	
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("GeoCan").password("qwerty").roles("ADMIN");
	}
}
