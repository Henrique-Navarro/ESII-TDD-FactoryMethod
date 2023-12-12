package com.es.fm.model;

import com.es.fm.interfaces.Fish;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("tropicalfish")
public class TropicalFish extends Fish {

    public String swim() {
        return "Tropical fish swimming gracefully";
    }
}
