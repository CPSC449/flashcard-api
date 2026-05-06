package com.flashcard.flashcard_api.controller;

import com.flashcard.flashcard_api.entity.Flashcard;
import com.flashcard.flashcard_api.service.FlashcardService;
import com.flashcard.flashcard_api.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/flashcards")
public class FlashcardController {
    @Autowired
    private FlashcardService service;

    @Autowired
    private JwtUtil jwtUtil;

    // Create flashcard
    @PostMapping
    public Flashcard create(@RequestBody Flashcard flashcard, HttpServletRequest request) {
        String token = jwtUtil.extractToken(request);
        String userId = jwtUtil.extractUserId(token);
        return service.createFlashcard(flashcard, userId);
    }

    // Get all flashcards
    @GetMapping
    public List<Flashcard> getAllFlashcards(HttpServletRequest request) {
        String token = jwtUtil.extractToken(request);
        String userId = jwtUtil.extractUserId(token);
        return service.getAllFlashcards(userId);
    }


    // Get flashcard by flashcard-id
    @GetMapping("/{id}")
    public Flashcard getById(@PathVariable String id, HttpServletRequest request) {
        String token = jwtUtil.extractToken(request);
        String userId = jwtUtil.extractUserId(token);
        return service.getFlashcardById(id, userId);
    }

    // update flashcard by id, user can only update its own flashcard
    @PutMapping("/{id}")
    public Flashcard update(@PathVariable String id, @RequestBody Flashcard flashcard, HttpServletRequest request) {
        String token = jwtUtil.extractToken(request);
        String userId = jwtUtil.extractUserId(token);
        return service.updateFlashcard(id, flashcard, userId);
    }

    // Delete flashcard, user can only delete its own flashcards
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id, HttpServletRequest request) {
        String token = jwtUtil.extractToken(request);
        String userId = jwtUtil.extractUserId(token);
        service.deleteFlashcard(id,userId);
    }
}