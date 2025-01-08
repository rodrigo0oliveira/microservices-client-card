package com.microservices.mscards.domain;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum FlagCard {
    MASTERCARD
    ,VISA;

    private String name;

     FlagCard(String name){
        this.name = name;
    }
}
