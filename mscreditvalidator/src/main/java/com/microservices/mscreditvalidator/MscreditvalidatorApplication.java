package com.microservices.mscreditvalidator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.microservices.mscreditvalidator.infra")
@EnableDiscoveryClient
public class MscreditvalidatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MscreditvalidatorApplication.class, args);
    }

}
