package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.interceptor.DemoInterceptor;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {
	@Autowired
	private DemoInterceptor demoInterceptor;

	public void addInterceptor(InterceptorRegistry registry) {
		registry.addInterceptor(demoInterceptor);
	}
}
