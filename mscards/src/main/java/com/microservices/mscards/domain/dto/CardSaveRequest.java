package com.microservices.mscards.domain.dto;


import com.microservices.mscards.domain.FlagCard;

import java.math.BigDecimal;

public record CardSaveRequest(String name, FlagCard flagCard, BigDecimal income, BigDecimal basicLimit) {
}
