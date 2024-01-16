package com.example.ribbonclient.configuration;

import com.google.common.base.Predicates;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// TODO enabled swagger 2
// TODO : this is a config class
@Configuration
@EnableSwagger2
@EnableEurekaClient
public class Swagger2UiConfiguration extends WebMvcConfigurerAdapter  {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.build()
				;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//http://localhost:8080/swagger2-demo/api/getStudents
		//http://localhost:8080/swagger2-demo/swagger-ui.html#/
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}


}
