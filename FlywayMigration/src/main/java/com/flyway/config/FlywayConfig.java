package com.flyway.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {
	@Autowired
	private Flyway flyway;
	public FlywayMigrationStrategy flywayMigrationStrategy() {
		return  flyway->{
			
		};
	}
}
