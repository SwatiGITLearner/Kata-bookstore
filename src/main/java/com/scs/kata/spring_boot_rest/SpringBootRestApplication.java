package com.scs.kata.spring_boot_rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableSwagger2
public class SpringBootRestApplication {

	/*@Bean
	public PasswordEncoder passwordEncoder()
	{
		//This will create encrypted Password
		return new BCryptPasswordEncoder();
	}*/
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApplication.class, args);
	}

}
