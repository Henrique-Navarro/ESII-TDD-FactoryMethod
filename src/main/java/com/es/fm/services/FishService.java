package com.es.fm.services;

import com.es.fm.interfaces.Fish;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class FishService {
    ArrayList<Fish> allFishes = new ArrayList<Fish>();

    public Fish save(Fish fish) {
        allFishes.add(fish);
        return fish;
    }

    public List<Fish> getAll() {
        return allFishes;
    }

    public Fish getById(Long id) {
        for (Fish fish : allFishes) if (fish.getId() == id) return fish;
        return null;
    }

    public void delete(Long id) {
        Iterator<Fish> iterator = allFishes.iterator();
        while (iterator.hasNext()) {
            Fish fish = iterator.next();
            if (fish.getId().equals(id)) {
                iterator.remove();
                break;
            }
        }
    }
}
