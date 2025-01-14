package com.microservices.mscreditvalidator.infra.client;

import com.microservices.mscreditvalidator.domain.ClientCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "mscards",path = "/cards")
public interface CardControllerClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<ClientCard>> getClientCards(@RequestParam(name = "cpf")String cpf);
}
