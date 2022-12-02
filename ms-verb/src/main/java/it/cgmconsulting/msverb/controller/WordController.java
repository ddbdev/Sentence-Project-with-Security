package it.cgmconsulting.msverb.controller;

import it.cgmconsulting.msverb.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WordController {

    private final WordService wordService;

    @Autowired
    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping
    public ResponseEntity<?> printList(){
        return new ResponseEntity<>(wordService.getRandomVerb(), HttpStatus.OK);
    }
}
