package com.microservices.mscards.application;

import com.microservices.mscards.domain.Card;
import com.microservices.mscards.domain.CardByClientResponse;
import com.microservices.mscards.domain.CardSaveRequest;
import com.microservices.mscards.domain.ClientCard;
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

    private final ClientCardService clientCardService;

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
    public ResponseEntity<List<Card>> getCardByIncomeLessThanEqual(@RequestParam(name = "income") Long income){
        List<Card> cards = cardService.getCardByIncomeLessThanEqual(income);
        return ResponseEntity.ok(cards);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CardByClientResponse>> getCardsByClient(@RequestParam(name = "cpf")String cpf){
        List<ClientCard> list = clientCardService.listCardsByCpf(cpf);
        List<CardByClientResponse> returnList = list.stream().map
                (CardByClientResponse::fromCard).toList();

        return ResponseEntity.ok(returnList);
    }

}
