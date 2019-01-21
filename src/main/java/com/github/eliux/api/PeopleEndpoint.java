package com.github.eliux.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
public class PeopleEndpoint {

    @GetMapping
    public String homePage(){
        return "This is the home page for the people api";
    }
}
