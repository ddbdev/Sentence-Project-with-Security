package it.cgmconsulting.msobject.controller;

import it.cgmconsulting.msobject.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordController {

    @Autowired
    private WordService objectService;

    @GetMapping("/")
    public String getObject() {
        return objectService.getObject();
    }
}
