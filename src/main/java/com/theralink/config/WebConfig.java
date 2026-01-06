package com.theralink.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final ClinicInterceptor clinicInterceptor;

	public WebConfig(ClinicInterceptor clinicInterceptor) {
		this.clinicInterceptor = clinicInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(clinicInterceptor).addPathPatterns("/**");
	}
}
