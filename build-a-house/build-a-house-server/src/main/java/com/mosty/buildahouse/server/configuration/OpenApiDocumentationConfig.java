package com.mosty.buildahouse.server.configuration;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition(
		info = @Info(title = "Build a House API", description = "API for Build a House", version = "0.0.1"),
		servers = {@Server(url = "/", description = "Local")})
public class OpenApiDocumentationConfig {
	//Tag and schema ordering will probably be applied here in the future
}
