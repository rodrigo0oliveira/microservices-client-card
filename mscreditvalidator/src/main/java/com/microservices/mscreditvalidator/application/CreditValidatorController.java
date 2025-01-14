package com.microservices.mscreditvalidator.application;

import com.microservices.mscreditvalidator.domain.ClientSituation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("creditvalidator")
@RequiredArgsConstructor
public class CreditValidatorController {

    private final CreditValidatorService creditValidatorService;

    @GetMapping(value = "/client-situation",params = "cpf")
    public ResponseEntity<ClientSituation> getClientSituation(@RequestParam(name = "cpf")String cpf) {
        ClientSituation clientSituation = creditValidatorService.getClientSituation(cpf);
        return ResponseEntity.ok(clientSituation);
    }
}
