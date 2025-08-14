package com.theralink;

import com.core.framework.common.exception.GlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.core.framework", "com.theralink" })
@EnableJpaRepositories(basePackages = { "com.theralink", "com.core.framework.repository" })
@EntityScan(basePackages = { "com.theralink.domain", "com.core.framework.domain" })
public class TheraLinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheraLinkApplication.class, args);
	}
}


