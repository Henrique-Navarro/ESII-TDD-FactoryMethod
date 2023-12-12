package com.es.fm.controllers;

import com.es.fm.factory.FishFactory;
import com.es.fm.interfaces.Fish;
import com.es.fm.services.FishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FishController {

    @Autowired
    private FishFactory fishFactory;

    @Autowired
    private FishService fishService;

    @GetMapping("/swim/{type}")
    public String swimFish(
            @PathVariable String type
    ) {
        Fish fish = fishFactory.createFish(type);
        return fish.swim();
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Fish> create(
            @RequestBody Fish fish
    ) {
        Fish newFish = fishService.save(fish);
        return ResponseEntity.status(HttpStatus.CREATED).body(newFish);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<Fish>> list() {
        return new ResponseEntity<>(
                fishService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Fish> getById(
            @PathVariable Long id
    ) {
        return new ResponseEntity<>(fishService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fish> updateFish(
            @PathVariable Long id,
            @RequestBody Fish updatedFish
    ) {
        Fish existingFish = fishService.getById(id);
        if (existingFish != null) {
            existingFish.setPeso(updatedFish.getPeso());
            existingFish.setExpectativaVida(updatedFish.getExpectativaVida());
            existingFish.setTemperamento(updatedFish.getTemperamento());

            Fish updated = fishService.save(existingFish);
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFish(
            @PathVariable Long id
    ) {
        Fish existingFish = fishService.getById(id);
        if (existingFish != null) {
            fishService.delete(id);
            return ResponseEntity.ok("Fish deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}