package com.es.fm.model;

import com.es.fm.interfaces.Fish;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("goldfish")
public class Goldfish extends Fish {

    public String swim() {
        return "Goldfish swimming gracefully";
    }
}