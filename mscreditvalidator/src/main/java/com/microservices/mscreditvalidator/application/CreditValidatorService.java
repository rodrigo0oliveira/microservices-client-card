package com.microservices.mscreditvalidator.application;

import com.microservices.mscreditvalidator.domain.*;

import com.microservices.mscreditvalidator.domain.dto.ResponseEvaluation;
import com.microservices.mscreditvalidator.infra.client.CardControllerClient;
import com.microservices.mscreditvalidator.infra.client.ClientControllerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditValidatorService{

    private final ClientControllerClient clientControllerClient;

    private final CardControllerClient cardControllerClient;

    public ClientSituation getClientSituation(String cpf){

        ResponseEntity<ClientData> clientData = clientControllerClient.getClientData(cpf);
        ResponseEntity<List<ClientCard>> clientCards = cardControllerClient.getClientCards(cpf);

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
            approved.setFlag(card.getFlag());
            approved.setApprovedLimit(approvedLimit);

            return approved;
        }).toList();

        return new ResponseEvaluation(approvedCards);
    }

    private BigDecimal calculateLimit(BigDecimal limit,Integer age){
        BigDecimal ageBigDecimal = BigDecimal.valueOf(age);
        BigDecimal fator = ageBigDecimal.divide(BigDecimal.valueOf(10));
        return fator.multiply(limit);
    }
}
