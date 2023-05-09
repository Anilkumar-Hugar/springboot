package com.organizationManagement.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.organizationManagement.service.UserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	@Autowired
	private UserService service;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(csrf -> csrf.disable())

				.authorizeHttpRequests(request -> request.requestMatchers("/swagger-ui/**").permitAll())
				.authorizeHttpRequests().requestMatchers("/authenticate/login").permitAll().and()
				.authorizeHttpRequests().requestMatchers("/authenticate/create").permitAll().and()
				.authorizeHttpRequests(auth -> auth.requestMatchers("/organization/**").authenticated())
				.authorizeHttpRequests().requestMatchers("/branch/**").authenticated().and().formLogin();
		httpSecurity.authorizeHttpRequests().anyRequest().permitAll();
		return httpSecurity.build();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(service).passwordEncoder(passwordEncoder());
		return authenticationManagerBuilder.build();

	}
}
