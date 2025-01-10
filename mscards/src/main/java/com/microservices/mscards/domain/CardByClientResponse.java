package com.microservices.mscards.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardByClientResponse {

    private String name;

    private FlagCard flagCard;

    private BigDecimal limit;

    public static CardByClientResponse fromCard(ClientCard clientCard) {
        return new CardByClientResponse(
                clientCard.getCard().getName(),
                clientCard.getCard().getFlagCard(),
                clientCard.getBasicLimit()
        );
    }


}
