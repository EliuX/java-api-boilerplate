package com.github.eliux.api;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static com.github.eliux.api.RoutingAPIEndpoint.THIRD_PARTY_API_URL;

@RunWith(MockitoJUnitRunner.class)
public class RoutingAPIEndpointTest {

    @Mock
    private RestTemplate testRestTemplate;

    @InjectMocks
    private RoutingAPIEndpoint routingAPIEndpoint = new RoutingAPIEndpoint();

    @Test
    public void callToLocalLogsShouldCallThirdPartyAPILogs() {
        final List<String> logs = Arrays.asList("log1", "log2", "log3", "logN");

        Mockito.when(testRestTemplate.getForObject(THIRD_PARTY_API_URL + "/logs", List.class))
                .thenReturn(logs);

        final List<String> obtainedReponse = routingAPIEndpoint.logs();

        Assert.assertEquals("The obtained response was not given by a call to a third party API",
                logs,
                obtainedReponse);
    }
}