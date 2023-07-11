package kr.co.belocal.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

// @EnableCaching
@SpringBootApplication
public class BelocalApplication {

	public static void main(String[] args) {
		SpringApplication.run(BelocalApplication.class, args);
	}

}
