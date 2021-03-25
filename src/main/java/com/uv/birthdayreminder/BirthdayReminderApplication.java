package com.uv.birthdayreminder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BirthdayReminderApplication {

	private static final Logger logger = LoggerFactory.getLogger(BirthdayReminderApplication.class);
	   
	public static void main(String[] args) {
		SpringApplication.run(BirthdayReminderApplication.class, args);
		logger.info("BirthdayReminderApplication Application Started !!!!");
	}

}
