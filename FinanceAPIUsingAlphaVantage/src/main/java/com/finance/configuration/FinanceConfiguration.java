package com.finance.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FinanceConfiguration {
	@Value("$(api.key)")
	private String apiKey;

	public String getKey() {
		return apiKey;
	}
}
