package com.example.match_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EntityScan(basePackages = "com.example.match_service.model")
public class MatchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatchServiceApplication.class, args);
	}

}
