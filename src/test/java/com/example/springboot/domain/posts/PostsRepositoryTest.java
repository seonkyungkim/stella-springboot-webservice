package com.example.springboot.domain.posts;

import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    void cleanup() {
        postsRepository.deleteAll();
    }

    /*
    * postsRepository.save(): execute insert/update query from table "posts". If id is is included, update query executes.
    * postRepository.finaAll(): select all data from table "posts". It needs a List<Posts> object in order to put the data in.
    * */
    @Test
    void posts_saved(){
        //given: Two parameters are valued and put data into postRepository.save() with the builder pattern.
        String title="Test title";
        String content="Test content";
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("stella@gmail.com")
                .build());

        //when: get the data from posts table using finaAll() and put it into postsList which is a Posts typed list.
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);     //index=0
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    void BaseTimeEntity_saved(){
        //given
        LocalDateTime now = LocalDateTime.of(2021,7,8,19,06,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>> createDate= "+posts.getCreatedDate()+", modifiedDate= "+posts.getModifiedDate());
        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}