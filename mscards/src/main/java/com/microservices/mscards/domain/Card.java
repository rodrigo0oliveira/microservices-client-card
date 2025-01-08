package com.microservices.mscards.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private FlagCard flagCard;

    private BigDecimal income;

    private BigDecimal basicLimit;

    public Card(String name,FlagCard flagCard,BigDecimal income,BigDecimal basicLimit){
        this.name = name;
        this.flagCard = flagCard;
        this.income = income;
        this.basicLimit = basicLimit;
    }


}
