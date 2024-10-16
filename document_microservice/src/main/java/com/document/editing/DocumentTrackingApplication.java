package com.document.editing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DocumentTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentTrackingApplication.class, args);
	}

}
