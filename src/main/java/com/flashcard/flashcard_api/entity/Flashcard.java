package com.flashcard.flashcard_api.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "flashcards")
@Data
public class Flashcard {
    @Id
    private String id;

    private String question;

    private String answer;

    @Field("created_at")
    private Date createdAt;

    @Field("user_id")
    private String userId;

}
