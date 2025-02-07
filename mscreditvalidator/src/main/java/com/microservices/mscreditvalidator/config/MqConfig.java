package com.microservices.mscreditvalidator.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {

    @Value("card-issuer")
    private String cardIssuerQueue;

    @Bean
    public Queue requestCardIssuerQueue() {
        return new Queue(cardIssuerQueue,true);
    }
}
