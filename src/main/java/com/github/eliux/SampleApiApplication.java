package com.github.eliux;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@SpringBootApplication
public class SampleApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApiApplication.class, args);
    }

    @GetMapping("/")
    public String homePage() {
        return "Welcome to my sample API";
    }

    @Bean
    public CommandLineRunner cmd() {
        return args -> {
            System.out.println("This is the CLI of Spring");
            System.out.println("These are the entered parameters");
            Stream.of(args).map("- "::concat).forEach(System.out::println);
        };
    }
}

