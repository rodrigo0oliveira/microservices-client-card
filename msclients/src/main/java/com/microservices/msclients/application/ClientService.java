package com.microservices.msclients.application;

import com.microservices.msclients.domain.Client;
import com.microservices.msclients.infra.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public Client saveClient(Client client){
        return clientRepository.save(client);
    }

    public Optional<Client> getClientByCpf(String cpf){
        return clientRepository.findByCpf(cpf);
    }
}
