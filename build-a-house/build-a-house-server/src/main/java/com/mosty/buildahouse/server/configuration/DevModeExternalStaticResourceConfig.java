package com.mosty.buildahouse.server.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnProperty(
    prefix = "app.dev-mode", 
    name = "enabled", 
    havingValue = "true", 
    matchIfMissing = false
)
public class DevModeExternalStaticResourceConfig implements WebMvcConfigurer {
	@Value("${app.dev-mode.path}")
	private String resourcePath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
				.addResourceLocations("file:" + resourcePath)
				.setCachePeriod(0)
	            .resourceChain(false);
	}
}
