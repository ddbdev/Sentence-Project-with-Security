package it.cgmconsulting.mssubject.controller;

import com.netflix.discovery.converters.Auto;
import it.cgmconsulting.mssubject.service.WordService;
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
        return new ResponseEntity<>(wordService.getRandomSubject(), HttpStatus.OK);
    }

}
