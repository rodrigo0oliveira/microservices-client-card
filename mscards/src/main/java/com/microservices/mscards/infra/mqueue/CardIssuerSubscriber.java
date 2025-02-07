package com.microservices.mscards.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.mscards.domain.Card;
import com.microservices.mscards.domain.ClientCard;
import com.microservices.mscards.domain.dto.RequestDataCardIssuer;
import com.microservices.mscards.infra.repository.CardRepository;
import com.microservices.mscards.infra.repository.ClientCardRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
@RequiredArgsConstructor
public class CardIssuerSubscriber {

    private final ObjectMapper objectMapper;

    private final CardRepository cardRepository;

    private final ClientCardRepository clientCardRepository;

    @RabbitListener(queues = "${mq.queues.card-issuer}")
    public void receiveRequestFromCardIssuer(@Payload String payload) throws JsonProcessingException {
        System.out.println(payload);
        RequestDataCardIssuer request = objectMapper.readValue(payload, RequestDataCardIssuer.class);

        Card card = cardRepository.findById(Long.valueOf(request.idCard()))
                .orElseThrow(()-> new NotFoundException("Card not found"));

        ClientCard clientCard = ClientCard.builder()
                .cpf(request.cpf())
                .basicLimit(new BigDecimal(request.limit()))
                .card(card)
                .build();

        clientCardRepository.save(clientCard);
    }
}
