package com.example.ribboneurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
// TODO : this is a eureka server (annotation)
@EnableEurekaServer
public class RibbonEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonEurekaServerApplication.class, args);
	}
}
