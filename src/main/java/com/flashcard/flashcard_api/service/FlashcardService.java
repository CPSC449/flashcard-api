package com.flashcard.flashcard_api.service;

import com.flashcard.flashcard_api.entity.Flashcard;
import com.flashcard.flashcard_api.repository.FlashcardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        flashcard.setUserId(userId);
        return repository.save(flashcard);
    }

    public List<Flashcard> getAllFlashcards(String userId) {
        return repository.findByUserId(userId);
    }

    public Flashcard getFlashcardById(String id, String userId) {
        Flashcard flashcard = repository.findById(id).orElse(null);
        if (!flashcard.getUserId().equals(userId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "This flashcard does not belong to you!"
            );
        }
        return flashcard;
    }

    public Flashcard updateFlashcard(String id, Flashcard updated, String userId) {
        Flashcard existing = getFlashcardById(id, userId);
        if (existing != null) {
            existing.setQuestion(updated.getQuestion());
            existing.setAnswer(updated.getAnswer());
            return repository.save(existing);
        }
        return null;
    }

    public void deleteFlashcard(String id, String userId) {
        Flashcard flashcard = getFlashcardById(id, userId);
        if (!flashcard.getUserId().equals(userId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "This flashcard does not belong to you!"
            );
        }
        repository.deleteById(id);
    }
}