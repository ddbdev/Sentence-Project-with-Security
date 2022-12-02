package it.cgmconsulting.msverb.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class WordService {

    @Value("${asw.sentence.wordservice.words}")
    private List<String> verbs;

    public String getRandomVerb(){
        Random random = new Random();
        return verbs.get(random.nextInt(verbs.size()));
    }
}
