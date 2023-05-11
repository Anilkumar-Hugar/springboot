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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.organizationManagement.jwtConfig.JwtTokenFilter;
import com.organizationManagement.service.UserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfigForJwt {
	@Autowired
	private JwtTokenFilter filter;
	@Autowired
	private UserService service;

	@Bean
<<<<<<< HEAD:src/main/java/com/organizationManagement/securityConfig/SecurityConfigForJwt.java
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		security.csrf().disable()
				.authorizeHttpRequests(request -> request.requestMatchers("/authenticate/login", "/authenticate/signin")
						.permitAll().anyRequest().authenticated())
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class).formLogin();
		return security.build();
=======
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(csrf -> csrf.disable())

				.authorizeHttpRequests(request -> request.requestMatchers("/v3/api-docs/**",
			            "/swagger-ui/**",
			            "/v2/api-docs/**",
			            "/swagger-resources/**").permitAll())
				.authorizeHttpRequests().requestMatchers("/authenticate/login").permitAll().and()
				.authorizeHttpRequests().requestMatchers("/authenticate/create").permitAll().and()
				.authorizeHttpRequests(auth -> auth.requestMatchers("/organization/**").authenticated())
				.authorizeHttpRequests().requestMatchers("/branch/**").authenticated().and().httpBasic();
		return httpSecurity.build();
>>>>>>> fab153694fabd2a63e88ecab8b5134873bfe8da1:src/main/java/com/organizationManagement/securityConfig/SecurityConfig.java
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
