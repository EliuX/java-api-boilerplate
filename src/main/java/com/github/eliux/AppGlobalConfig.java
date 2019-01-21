package com.github.eliux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppGlobalConfig implements ApplicationRunner {

    @Autowired
    private AbstractApplicationContext appContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("This is the CLI of Spring");
        System.out.println("These are the entered parameters");

        if (args.containsOption("exit")) {
            SpringApplication.exit(appContext);
        }

        args.getNonOptionArgs().stream()
                .map("- "::concat)
                .forEach(System.out::println);

        args.getOptionNames().stream()
                .map("-> "::concat)
                .forEach(System.out::println);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
