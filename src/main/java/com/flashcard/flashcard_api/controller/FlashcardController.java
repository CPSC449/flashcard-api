package com.flashcard.flashcard_api.controller;

import com.flashcard.flashcard_api.entity.Flashcard;
import com.flashcard.flashcard_api.service.FlashcardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flashcards")
public class FlashcardController {

    private final FlashcardService service;

    public FlashcardController(FlashcardService service) {
        this.service = service;
    }

    // Create flashcard
    @PostMapping
    public Flashcard create(@RequestBody Flashcard flashcard) {
        return service.createFlashcard(flashcard);
    }

    // Get all flashcards
    @GetMapping
    public List<Flashcard> getAllFlashcards() {
        return service.getAllFlashcards();
    }


    // Get flashcard by flashcard-id
    @GetMapping("/{id}")
    public Flashcard getById(@PathVariable String id) {
        return service.getFlashcardById(id);
    }

    // update flashcard by id, user can only update its own flashcard
    @PutMapping("/{id}")
    public Flashcard update(@PathVariable String id, @RequestBody Flashcard flashcard) {
        return service.updateFlashcard(id, flashcard);
    }

    // Delete flashcard, user can only delete its own flashcards
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deleteFlashcard(id);
    }
}