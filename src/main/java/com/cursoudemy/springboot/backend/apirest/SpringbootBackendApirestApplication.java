package com.cursoudemy.springboot.backend.apirest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootBackendApirestApplication {

	final static Logger logger = LoggerFactory.getLogger(SpringbootBackendApirestApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(SpringbootBackendApirestApplication.class, args);
		logger.info("##############################");
		logger.info("####### ANDANDO CHAVAL #######");
		logger.info("##############################");

	}

}