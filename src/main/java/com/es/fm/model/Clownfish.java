package com.es.fm.model;

import com.es.fm.interfaces.Fish;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("clownfish")
public class Clownfish extends Fish {

    public String swim() {
        return "Clownfish swimming playfully";
    }
}