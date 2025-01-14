package com.microservices.mscreditvalidator.application;

import com.microservices.mscreditvalidator.domain.ClientData;
import com.microservices.mscreditvalidator.domain.ClientSituation;

import com.microservices.mscreditvalidator.infra.ClientControllerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditValidatorService{

    private final ClientControllerClient clientControllerClient;

    public ClientSituation getClientSituation(String cpf){
        ResponseEntity<ClientData> clientData = clientControllerClient.getClientData(cpf);
        return ClientSituation.builder()
                .clientData(clientData.getBody())
                .clientCardList(null)
                .build();
    }
}
