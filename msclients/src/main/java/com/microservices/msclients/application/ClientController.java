package com.microservices.msclients.application;


import com.microservices.msclients.domain.Client;
import com.microservices.msclients.domain.ClientSaveRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public String status(){
        log.info("Register new instance of client microservice");
        return "status";
    }

    @PostMapping
    public ResponseEntity<Client> saveClient(@RequestBody ClientSaveRequest client){
        Client newCLient = clientService.saveClient(client.toModel());

        return new ResponseEntity<>(newCLient, (HttpStatus.CREATED));
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<?> getClientByCpf(@RequestParam(name = "cpf") String cpf){
        Optional<Client> client = clientService.getClientByCpf(cpf);
        if(client.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(client,HttpStatus.OK);

    }


}
