package com.example.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
 * Never create setters in the Entity class.
 * If fields need to be changed, use a method which contains each purpose and intention.
 * */
@Getter
@NoArgsConstructor
@Entity //An entity class linked with a table named by the underscore convention.
public class Posts {

    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //auto_increment enabled
    private Long id;

    @Column(length = 500, nullable = false)     //default varchar size is 255.
    private String title;

    @Column(columnDefinition = "text", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title=title;
        this.content=content;
    }

}
