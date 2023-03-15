package com.organizationManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "organizationManagement",
		version = "1.0",
		description = "organization and branch operations",
		contact = @Contact(name = "Anil",email = "Anil@gmail.com",url = "seneca.com")
		))
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
