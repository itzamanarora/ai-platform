package com.aman.ai_platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AiPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiPlatformApplication.class, args);
	}

}
