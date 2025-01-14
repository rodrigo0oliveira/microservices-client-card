package com.microservices.mscreditvalidator.infra.client;

import com.microservices.mscreditvalidator.domain.ClientData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msclients",path = "/clients")
public interface ClientControllerClient {

    @GetMapping(params = "cpf")
    ResponseEntity<ClientData> getClientData(@RequestParam(name = "cpf") String cpf);
}
