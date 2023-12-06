package com.abeck.ssfa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.abeck.ssfa")
public class SSfaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SSfaApplication.class, args);
	}
}
