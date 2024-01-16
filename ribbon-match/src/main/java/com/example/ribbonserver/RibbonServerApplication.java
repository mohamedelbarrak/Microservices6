package com.example.ribbonserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
// TODO : this is a Discovery Client
@EnableDiscoveryClient //on peut etre decouvert sur plusieur instance
@EnableEurekaClient
//@EnableAdminServer
public class RibbonServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonServerApplication.class, args);
	}
}
