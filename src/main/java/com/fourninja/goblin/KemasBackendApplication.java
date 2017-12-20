package com.fourninja.goblin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.fourninja.goblin.config.AppConfig;
import com.fourninja.goblin.config.JpaConfig;
import com.fourninja.goblin.config.SecurityConfig;
import com.fourninja.goblin.config.ServiceConfig;
import com.fourninja.goblin.config.TransactionConfig;

@SpringBootApplication()
@Configuration
@Import(value = {
		AppConfig.class,
		JpaConfig.class,
		TransactionConfig.class,
		ServiceConfig.class,
        SecurityConfig.class
} )
public class KemasBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(KemasBackendApplication.class, args);
	}
}
