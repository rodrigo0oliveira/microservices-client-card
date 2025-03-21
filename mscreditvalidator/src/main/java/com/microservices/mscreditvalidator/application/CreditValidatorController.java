package com.microservices.mscreditvalidator.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservices.mscreditvalidator.domain.ClientSituation;
import com.microservices.mscreditvalidator.domain.dto.EvaluationData;
import com.microservices.mscreditvalidator.domain.dto.RequestDataCardIssuer;
import com.microservices.mscreditvalidator.domain.dto.RequestProtocol;
import com.microservices.mscreditvalidator.domain.dto.ResponseEvaluation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<ResponseEvaluation> doEvaluation(@RequestBody EvaluationData data){
        ResponseEvaluation responseEvaluation = creditValidatorService.doEvaluation(data.cpf(), data.income());
        return ResponseEntity.ok(responseEvaluation);
    }

@PostMapping("/request-card")
    public ResponseEntity<RequestProtocol> requestCardIssuer(@RequestBody RequestDataCardIssuer request) throws JsonProcessingException {
        RequestProtocol requestProtocol =  creditValidatorService.cardIssuer(request);
        return ResponseEntity.ok(requestProtocol);
    }
}
