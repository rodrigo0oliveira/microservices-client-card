package com.microservices.mscards.application;

import com.microservices.mscards.domain.ClientCard;
import com.microservices.mscards.infra.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientCardService {

    private final ClientCardRepository clientCardRepository;

    public List<ClientCard> listCardsByCpf(String cpf){
        return clientCardRepository.findByCpf(cpf);
    }
}
