package com.example.springcache1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication()
public class SpringEnvVarApp {

	@Value("${var1}")
	String var1;

	@Bean
	boolean bean1() {
		System.out.println("var1 = " + var1);
		return true;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringEnvVarApp.class, args);
	}
}
