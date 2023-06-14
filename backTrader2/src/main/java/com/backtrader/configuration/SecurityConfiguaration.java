package com.backtrader.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.backtrader.jwtutils.JwtTokenFilter;
import com.backtrader.service.UserService;


@EnableWebSecurity
public class SecurityConfiguaration {
	@Autowired
	private JwtTokenFilter filter;
	@Autowired
	private UserService service;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		security.csrf(token -> token.disable())
				.authorizeHttpRequests(request -> request.requestMatchers("/backtrader/login", "/backtrader/create")
						.permitAll())
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				;
		return security.build();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
		AuthenticationManagerBuilder managerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
		managerBuilder.userDetailsService(service).passwordEncoder(passwordEncoder());
		return managerBuilder.build();
	}
}
