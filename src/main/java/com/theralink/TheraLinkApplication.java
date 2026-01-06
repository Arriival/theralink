package com.theralink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.theralink" })
@EnableJpaRepositories(basePackages = { "com.theralink" })
@EntityScan(basePackages = { "com.theralink.domain", "com.theralink.domain" })
public class TheraLinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheraLinkApplication.class, args);
	}
}


