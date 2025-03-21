package com.microservices.mscreditvalidator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientData {

    private String id;

    private String name;

    private Integer age;
}
