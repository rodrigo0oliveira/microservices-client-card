package com.microservices.mscreditvalidator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientCard {

    private String name;

    private String flagCard;

    private BigDecimal basicLimit;
}
