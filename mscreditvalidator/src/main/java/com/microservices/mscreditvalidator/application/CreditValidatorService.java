package com.microservices.mscreditvalidator.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservices.mscreditvalidator.domain.*;

import com.microservices.mscreditvalidator.domain.dto.RequestDataCardIssuer;
import com.microservices.mscreditvalidator.domain.dto.RequestProtocol;
import com.microservices.mscreditvalidator.domain.dto.ResponseEvaluation;
import com.microservices.mscreditvalidator.infra.client.CardControllerClient;
import com.microservices.mscreditvalidator.infra.client.ClientControllerClient;
import com.microservices.mscreditvalidator.infra.queue.RequestCardIssuerPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreditValidatorService{

    private final ClientControllerClient clientControllerClient;

    private final CardControllerClient cardControllerClient;

    private final RequestCardIssuerPublisher requestCardIssuerPublisher;

    public ClientSituation getClientSituation(String cpf){

        ResponseEntity<ClientData> clientData = clientControllerClient.getClientData(cpf);
        ResponseEntity<List<ClientCard>> clientCards = cardControllerClient.getClientCards(cpf);

        log.info(clientCards.getBody().toString());

        return ClientSituation.builder()
                .clientData(clientData.getBody())
                .clientCardList(clientCards.getBody())
                .build();
    }

    public ResponseEvaluation doEvaluation(String cpf,Long renda){
        ResponseEntity<ClientData> clientData = clientControllerClient.getClientData(cpf);
        ResponseEntity<List<Card>> listCard = cardControllerClient.getCardByIncomeLessThanEqual(renda);

        List<Card> cards = listCard.getBody();
        ClientData clientDataBody = clientData.getBody();

        List<ApprovedCard> approvedCards = cards.stream().map(card -> {
            BigDecimal approvedLimit = calculateLimit(card.getBasicLimit(),clientDataBody.getAge());

            ApprovedCard approved = ApprovedCard.builder().build();
            approved.setCard(card.getName());
            approved.setFlag(card.getFlagCard().toString());
            approved.setApprovedLimit(approvedLimit);

            return approved;
        }).toList();

        return new ResponseEvaluation(approvedCards);
    }

    public RequestProtocol cardIssuer(RequestDataCardIssuer requestDataCardIssuer) throws JsonProcessingException {
        requestCardIssuerPublisher.requestCard(requestDataCardIssuer);
        return new RequestProtocol(UUID.randomUUID().toString());
    }

    private BigDecimal calculateLimit(BigDecimal limit,Integer age){
        BigDecimal ageBigDecimal = BigDecimal.valueOf(age);
        BigDecimal fator = ageBigDecimal.divide(BigDecimal.valueOf(10));
        return fator.multiply(limit);
    }
}
