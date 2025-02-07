package com.microservices.mscreditvalidator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Card {

    private Long id;

    private String name;

    private FlagCard flagCard;

    private BigDecimal basicLimit;
}
