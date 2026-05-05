package com.flashcard.flashcard_api.repository;

import com.flashcard.flashcard_api.entity.Flashcard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FlashcardRepository extends MongoRepository<Flashcard, String> {
}