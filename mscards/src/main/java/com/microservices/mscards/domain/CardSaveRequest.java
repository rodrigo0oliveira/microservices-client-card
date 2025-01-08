package com.microservices.mscards.domain;


import java.math.BigDecimal;

public record CardSaveRequest(String name, FlagCard flagCard, BigDecimal income, BigDecimal basicLimit) {
}
