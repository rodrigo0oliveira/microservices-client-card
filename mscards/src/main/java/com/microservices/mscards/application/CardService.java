package com.microservices.mscards.application;

import com.microservices.mscards.domain.Card;
import com.microservices.mscards.domain.dto.CardSaveRequest;
import com.microservices.mscards.infra.repository.CardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardService {

    private final CardRepository cardRepository;

    @Transactional
    public void save(CardSaveRequest card){
        Card newCard = Card.builder()
                .name(card.name())
                .flagCard(card.flagCard())
                .income(card.income())
                .basicLimit(card.basicLimit())
                .build();
        log.info("Saving Card"+ newCard.toString());
        cardRepository.save(newCard);
    }

    public List<Card> getCardByIncomeLessThanEqual(Long income){
        BigDecimal decimalIncome = BigDecimal.valueOf(income);
        return cardRepository.findByIncomeLessThanEqual(decimalIncome);
    }
}
