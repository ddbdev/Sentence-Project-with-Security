package it.cgmconsulting.mssubject.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class WordService {

    @Value("${asw.sentence.wordservice.words}")
    private List<String> subjects;

    public String getRandomSubject(){
        Random random = new Random();
        return subjects.get(random.nextInt(subjects.size()));
    }

}
