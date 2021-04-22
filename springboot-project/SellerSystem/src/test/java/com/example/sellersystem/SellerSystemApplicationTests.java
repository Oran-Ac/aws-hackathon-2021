package com.example.sellersystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class SellerSystemApplicationTests {

    @Test
    void contextLoads() {
        File file = new File("./usr/haha1");
        if(!file.exists()) {
            if(file.mkdirs()) {
                System.out.println("yes");
                System.out.println(file.getAbsolutePath());
            } else {
                System.out.println(file.getAbsolutePath());
            }
        }
    }

}
