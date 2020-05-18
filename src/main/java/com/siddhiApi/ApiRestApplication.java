package com.siddhiApi;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApiRestApplication {

	//StreamJunction streamJunction = new StreamJunction();
	Logger logger = LoggerFactory.getLogger(ApiRestApplication.class);

	public static void main(String[] args) {
		BasicConfigurator.configure();
		SpringApplication.run(ApiRestApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(){
		logger.debug("Llego a hello");
		String s = "Hello World";
		return s;
	}
}
