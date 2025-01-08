package com.microservices.mscards.application;

import com.microservices.mscards.domain.Card;
import com.microservices.mscards.domain.CardSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping
    public String status(){
        return "status";
    }

    @PostMapping
    public ResponseEntity saveCard(@RequestBody CardSaveRequest cardSaveRequest){
        cardService.save(cardSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity getCardByIncomeLessThanEqual(@RequestParam(name = "income") Long income){
        List<Card> cards = cardService.getCardByIncomeLessThanEqual(income);
        return ResponseEntity.ok(cards);
    }
}
