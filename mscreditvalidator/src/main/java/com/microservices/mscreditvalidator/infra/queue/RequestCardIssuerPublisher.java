package com.microservices.mscreditvalidator.infra.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.mscreditvalidator.domain.dto.RequestDataCardIssuer;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequestCardIssuerPublisher {

    private final RabbitTemplate rabbitTemplate;

    private final Queue cardIssuerQueue;

    private final ObjectMapper objectMapper;

    public void requestCard(RequestDataCardIssuer request) throws JsonProcessingException {
        String stringRequest = convertIntoString(request);
        rabbitTemplate.convertAndSend(cardIssuerQueue.getActualName(),stringRequest);
    }

    public String convertIntoString(RequestDataCardIssuer request) throws JsonProcessingException {
        return objectMapper.writeValueAsString(request);
    }
}
