package com.github.eliux.api;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static com.github.eliux.api.RoutingAPIEndpoint.THIRD_PARTY_API_URL;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoutingAPIEndpointTest {

    @LocalServerPort
    private int port;

    @MockBean
    private RestTemplate testRestTemplate;

    @Test
    public void callToLocalLogsShouldCallThirdPartyAPILogs() {
        final List<String> logs = Arrays.asList("log1", "log2", "log3", "logN");

        Mockito.when(testRestTemplate.getForObject(THIRD_PARTY_API_URL + "/logs", List.class))
                .thenReturn(logs);

        final RestTemplate clientRestTemplate = new RestTemplate();

        final List<String> response = clientRestTemplate.getForObject(
                String.format("http://localhost:%s/api/logs", port),
                List.class
        );

        Assert.assertEquals("The obtained response was not given by a call to a third party API",
                logs,
                response);
    }
}