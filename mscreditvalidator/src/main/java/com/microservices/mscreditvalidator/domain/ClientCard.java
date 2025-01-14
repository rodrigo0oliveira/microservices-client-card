package com.microservices.mscreditvalidator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientCard {

    private String name;

    private String flagCard;

    private String basicLimit;
}
