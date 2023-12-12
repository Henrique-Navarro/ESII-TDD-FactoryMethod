package com.es.fm.controllers;

import com.es.fm.interfaces.Fish;
import com.es.fm.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Map<Long, User> users = new HashMap<>();

    @PostMapping
    public User createUser(
            @RequestBody User user
    ) {
        users.put(user.getId(), user);
        return user;
    }

    @GetMapping("/list")
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    @GetMapping("/{userId}")
    public User getUserById(
            @PathVariable Long userId
    ) {
        return users.get(userId);
    }

    @PostMapping("/{userId}/addFish")
    public String addFishToUser(
            @PathVariable Long userId,
            @RequestBody Fish fish
    ) {
        User user = users.get(userId);
        if (user != null) {
            ArrayList<Fish> userFishes = user.getFishes();
            if (userFishes == null) {
                userFishes = new ArrayList<>();
            }
            userFishes.add(fish);
            user.setFishes(userFishes);
            users.put(userId, user);
            return "Fish added to user successfully";
        }
        return "User not found";
    }

    @GetMapping("/{userId}/fishes")
    public List<Fish> getUserFishes(
            @PathVariable Long userId
    ) {
        // Lógica para obter os peixes do usuário
        User user = users.get(userId);
        if (user != null) {
            return user.getFishes();
        }
        return null;
    }

    @DeleteMapping("/{userId}/removeFish/{fishId}")
    public String removeFishFromUser(
            @PathVariable Long userId,
            @PathVariable Long fishId
    ) {
        User user = users.get(userId);
        if (user != null) {
            ArrayList<Fish> userFishes = user.getFishes();
            if (userFishes != null) {
                boolean removed = userFishes.removeIf(fish -> fish.getId().equals(fishId));
                if (removed) {
                    user.setFishes(userFishes);
                    users.put(userId, user);
                    return "Fish removed from user successfully";
                }
                return "Fish not found in user's list";
            }
            return "User's fish list is empty";
        }
        return "User not found";
    }

    @PutMapping("/{userId}")
    public String updateUser(
            @PathVariable Long userId,
            @RequestBody User updatedUser
    ) {
        User user = users.get(userId);
        if (user != null) {
            user.setName(updatedUser.getName());
            user.setFishes(updatedUser.getFishes());
            users.put(userId, user);
            return "User updated successfully";
        }
        return "User not found";
    }
}
