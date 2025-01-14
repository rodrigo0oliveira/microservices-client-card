package com.microservices.mscreditvalidator.application;

import com.microservices.mscreditvalidator.domain.ClientCard;
import com.microservices.mscreditvalidator.domain.ClientData;
import com.microservices.mscreditvalidator.domain.ClientSituation;

import com.microservices.mscreditvalidator.infra.client.CardControllerClient;
import com.microservices.mscreditvalidator.infra.client.ClientControllerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
}
