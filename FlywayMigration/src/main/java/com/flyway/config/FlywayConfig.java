package com.flyway.config;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {
	public FlywayMigrationStrategy flywayMigrationStrategy() {
		return  maigration->{
			
		};
	}
}
