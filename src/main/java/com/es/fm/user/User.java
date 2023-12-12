package com.es.fm.user;

import com.es.fm.interfaces.Fish;

import java.util.ArrayList;

public class User {
    private Long id;
    private String name;
    private ArrayList<Fish> fishes;

    public User(Long id, String name, ArrayList<Fish> fishes) {
        this.id = id;
        this.name = name;
        this.fishes = fishes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Fish> getFishes() {
        return fishes;
    }

    public void setFishes(ArrayList<Fish> fishes) {
        this.fishes = fishes;
    }
}
