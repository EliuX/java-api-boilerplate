package com.github.eliux.api;

import com.github.eliux.dm.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoutingAPI {

    static final String THIRD_PARTY_API_URL = "http://localhost:3000";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/logs")
    public List<String> logs() {
        List<String> result = restTemplate.getForObject(THIRD_PARTY_API_URL + "/logs", List.class);
        //Use exchange for more complex objects
        return result;
    }

    @GetMapping("/stats")
    public List<Statistics> stats() {
        List<Statistics> result = restTemplate.getForObject(THIRD_PARTY_API_URL + "/stats", List.class);
        //Use exchange for more complex objects
        return result;
    }
}
