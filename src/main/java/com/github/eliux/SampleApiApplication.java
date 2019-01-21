package com.github.eliux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.logging.Logger;

@RestController
@SpringBootApplication
public class SampleApiApplication {

    private static final Logger LOG
            = Logger.getLogger(SampleApiApplication.class.getName());

    @Autowired
    private Environment env;

    @Autowired
    private AbstractApplicationContext appContext;

    public static void main(String[] args) {
        SpringApplication.run(SampleApiApplication.class, args);
        LOG.info("Sample App Started!");
    }

    @GetMapping("/")
    public String homePage() {
        return "Welcome to my sample API";
    }

    @PostConstruct
    public void initApplication() throws RuntimeException {
        String activeProfiles = Arrays.toString(env.getActiveProfiles());

        appContext.registerShutdownHook();

        if (activeProfiles.startsWith("error")) {
            SpringApplication.exit(appContext);
        }
    }
}

