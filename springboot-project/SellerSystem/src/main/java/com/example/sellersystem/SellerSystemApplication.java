package com.example.sellersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

//@SpringBootApplication(exclude={MongoAutoConfiguration.class})
@SpringBootApplication
public class SellerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SellerSystemApplication.class, args);
    }

}
