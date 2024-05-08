package com.company.tracktime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@EnableScheduling
@SpringBootApplication
@EnableAsync
public class TrackTimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackTimeApplication.class, args);
	}

	@Bean
	public Executor jobExecutor() {
		return Executors.newCachedThreadPool();
	}

}
