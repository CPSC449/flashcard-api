package com.flashcard.flashcard_api.repository;

import com.flashcard.flashcard_api.entity.Flashcard;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FlashcardRepository extends MongoRepository<Flashcard, String> {
    List<Flashcard> findByUserId(String userId);
}