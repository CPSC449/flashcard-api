package com.flashcard.flashcard_api.service;

import com.flashcard.flashcard_api.entity.Flashcard;
import com.flashcard.flashcard_api.repository.FlashcardRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FlashcardService {

    private final FlashcardRepository repository;

    public FlashcardService(FlashcardRepository repository) {
        this.repository = repository;
    }

    public Flashcard createFlashcard(Flashcard flashcard, String userId) {
        flashcard.setCreatedAt(new Date());
        return repository.save(flashcard);
    }

    public List<Flashcard> getAllFlashcards(String userId) {
        return repository.findByUserId(userId);
    }

    public Flashcard getFlashcardById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Flashcard updateFlashcard(String id, Flashcard updated) {
        Flashcard existing = getFlashcardById(id);
        if (existing != null) {
            existing.setQuestion(updated.getQuestion());
            existing.setAnswer(updated.getAnswer());
            return repository.save(existing);
        }
        return null;
    }

    public void deleteFlashcard(String id) {
        repository.deleteById(id);
    }
}