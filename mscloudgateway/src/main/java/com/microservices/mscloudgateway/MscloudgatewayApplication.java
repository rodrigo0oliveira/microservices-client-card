package com.microservices.mscloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableDiscoveryClient
public class MscloudgatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscloudgatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder){
		return builder
				.routes()
				.route(r -> r.path("/clients/**").uri("lb://msclients"))
				.route(r->r.path("/cards/**").uri("lb://mscards"))
				.route(r->r.path("/creditvalidator/**").uri("lb://mscreditvalidator"))
				.route(r->r.path("/h2-console/**").uri("lb://h2console"))
				.build();
	}

}
