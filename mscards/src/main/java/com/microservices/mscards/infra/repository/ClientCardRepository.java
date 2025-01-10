package com.microservices.mscards.infra.repository;

import com.microservices.mscards.domain.ClientCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientCardRepository extends JpaRepository<ClientCard, Long> {

    @Query("select c from ClientCard c where c.cpf = ?1")
    List<ClientCard> findByCpf(String cpf);
}
