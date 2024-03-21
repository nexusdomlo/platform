package com.example.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;


@SpringBootApplication
public class PlatformApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(PlatformApplication.class, args);
    }

}
