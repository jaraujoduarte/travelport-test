package com.travelport.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.travelport.*" })
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}