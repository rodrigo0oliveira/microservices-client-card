package com.microservices.msclients.domain;

import lombok.Data;

@Data
public class ClientSaveRequest {

    private String name;

    private String cpf;

    private Integer age;

    public Client toModel(){
        return new Client(name,cpf,age);
    }

}
