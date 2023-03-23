package com.hackernews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/*
 *  @author Pratik Hajare
 *  
 * */
@EnableCaching
@ComponentScan({ "com.hackernews" })
@SpringBootApplication
public class HackernewsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackernewsAppApplication.class, args);
	}

}
