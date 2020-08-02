package com.qimeng.tools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MailApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MailApplication.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MailApplication.class);
	}

}
