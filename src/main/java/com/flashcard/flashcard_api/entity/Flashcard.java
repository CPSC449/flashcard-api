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

    @Field
    private String question;

    @Field
    private String answer;

    @Field("createdAt")
    private Date createdAt;

}
