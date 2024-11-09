package com.hhplus.commerce._3weeks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        //test
        SpringApplication.run(Application.class, args);
    }

}
