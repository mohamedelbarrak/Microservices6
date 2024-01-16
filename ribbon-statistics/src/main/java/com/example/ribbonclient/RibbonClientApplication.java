package com.example.ribbonclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;

@EnableDiscoveryClient
@EnableEurekaClient
// TODO : this is a Discovery client
@SpringBootApplication
// TODO : configure a ribbon client (this is a server)
@RibbonClients({
		@RibbonClient(name = "statistics", configuration = RibbonConfiguration.class)
})
public class RibbonClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonClientApplication.class, args);
	}
}
