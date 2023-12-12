package com.es.fm.factory;

import com.es.fm.interfaces.Factory;
import com.es.fm.interfaces.Fish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FishFactory implements Factory {

    private final Map<String, Fish> fishMap = new HashMap<>();

    @Autowired
    public FishFactory(
            @Qualifier("goldfish") Fish goldfish,
            @Qualifier("clownfish") Fish clownfish,
            @Qualifier("angelfish") Fish angelfish,
            @Qualifier("tropicalfish") Fish tropicalfish,
            @Qualifier("freshwaterfish") Fish freshwaterfish
    ) {
        fishMap.put("goldfish", goldfish);
        fishMap.put("clownfish", clownfish);
        fishMap.put("angelfish", angelfish);
        fishMap.put("tropicalfish", tropicalfish);
        fishMap.put("freshwaterfish", freshwaterfish);
    }

    @Override
    public Fish createFish(
            String fishType
    ) {
        Fish fish = fishMap.get(fishType);
        if (fish == null) {
            throw new IllegalArgumentException("Invalid fish type");
        }
        return fish;
    }
}