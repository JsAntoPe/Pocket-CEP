package com.siddhiApi;

import com.siddhiApi.multithreading.EventExecutor;
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
		for (int i=0; i<5;++i)
			EventExecutor.getEventExecutor().executeConsumer();
		SpringApplication.run(ApiRestApplication.class, args);
	}
}
