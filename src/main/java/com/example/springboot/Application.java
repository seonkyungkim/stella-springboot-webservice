package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
* With @SpringBootApplication, it works as a main class of the project.
* It automatically sets basic settings for Springboot, reading and writing Spring beans.
* It has to be located to the root path so that Springboot starts to read the configuration from its location.
* */
@EnableJpaAuditing  //JPA auditing enabled
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); //Once it runs, embeded WAS will be started
    }
}
