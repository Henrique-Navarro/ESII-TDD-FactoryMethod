package com.es.fm.model;

import com.es.fm.interfaces.Fish;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("freshwaterfish")
public class FreshwaterFish extends Fish {

    public String swim() {
        return "Freshwater fish swimming gently";
    }
}
