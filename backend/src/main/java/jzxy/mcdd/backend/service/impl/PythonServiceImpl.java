package jzxy.mcdd.backend.service.impl;

import jzxy.mcdd.backend.service.PythonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * PythonServiceImpl
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/30 23:07
 */
@Service
@RequiredArgsConstructor
public class PythonServiceImpl implements PythonService {
    private final RestTemplate restTemplate;
    @Override
    public String getHelloMessage() {
        String url = "http://localhost:5000/hello";
        return restTemplate.getForObject(url, String.class);
    }

    public String getGreetingMessage(String name) {
        URI targetUrl = UriComponentsBuilder.fromUriString("http://localhost:5000/greet")
                .queryParam("name", name)
                .build()
                .toUri();

        return restTemplate.getForObject(targetUrl, String.class);
    }

    public String getItem(Integer itemId) {
        String url = "http://localhost:5000/item/{id}";
        return restTemplate.getForObject(url, String.class, itemId);
    }
}
