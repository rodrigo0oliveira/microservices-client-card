package com.microservices.mscreditvalidator;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.microservices.mscreditvalidator.infra.client")
@EnableDiscoveryClient
@EnableRabbit
public class MscreditvalidatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MscreditvalidatorApplication.class, args);
    }

}
