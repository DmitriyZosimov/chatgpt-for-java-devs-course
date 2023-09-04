package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class GoogleMapsServiceImpl implements GoogleMapsService {
    private static final String GOOGLE_MAPS_API_BASE_URL = "https://maps.googleapis.com/maps/api/directions/json";

    private final RestTemplate restTemplate;
    @Value("${google.maps.api.key}")
    private String apiKey;  // Your Google Maps API Key

    @Autowired
    public GoogleMapsServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getDirections(String origin, String destination) {
        String url = GOOGLE_MAPS_API_BASE_URL +
                "?origin=" + URLEncoder.encode(origin, StandardCharsets.UTF_8) +
                "&destination=" + URLEncoder.encode(destination, StandardCharsets.UTF_8) +
                "&key=" + apiKey;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }

        return null;
    }
}

