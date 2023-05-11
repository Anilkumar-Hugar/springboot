package com.organizationManagement.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
@Configuration
<<<<<<< HEAD
@SecurityScheme(name = "basicAuth",scheme = "basic",type = SecuritySchemeType.APIKEY,in = SecuritySchemeIn.HEADER)
=======
@SecurityScheme(name = "basicAuth",scheme = "basic",type = SecuritySchemeType.HTTP,in = SecuritySchemeIn.DEFAULT)
	

>>>>>>> fab153694fabd2a63e88ecab8b5134873bfe8da1
public class SwaggerConfig {
	@Bean
	 GroupedOpenApi openapi() {
		return GroupedOpenApi
				.builder()
				.group("Organization Management")
				.displayName("organization Management")
				.pathsToMatch()
				.packagesToScan("com.organizationManagement.controller")
				.build();
	}
	@Bean
    OpenAPI api() {
		return new OpenAPI()
				.info(new Info().title("Organization Managemenet")
						.description("Crud operations using organization and branch entities")
						.contact(new Contact())
						.title("Organization Management")
						.version("1.0")
						);
	}
	
}
