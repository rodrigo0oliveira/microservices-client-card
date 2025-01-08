package com.microservices.mscards.infra.repository;

import com.microservices.mscards.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {

    @Query("select c from Card c where c.income < ?1 or c.income = ?1")
    List<Card> findByIncomeLessThanEqual(BigDecimal income);
}
