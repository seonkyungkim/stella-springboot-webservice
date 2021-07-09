package com.example.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/*
* BaseTimeEntity becomes a super class of all the entities to control their createDate, modifiedDate functions.
* */
@Getter
@MappedSuperclass   //Designates a class whose mapping info is applied to the entities that inherit from it.
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreatedDate    //Time is auto-saved when Entities create and save.
    private LocalDateTime createdDate;

    @LastModifiedDate   //Time is auto-saved when Entity values are changed.
    private LocalDateTime modifiedDate;
}
