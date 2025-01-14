package com.microservices.mscreditvalidator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientSituation {

    private ClientData clientData;

    private List<ClientCard> clientCardList;
}
