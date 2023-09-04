package com.example.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GoogleMapsServiceImplTest {

    @InjectMocks
    private GoogleMapsServiceImpl googleMapsService;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void testGetDirections_Success() {
        // Mock the response from the Google Maps API
        ResponseEntity<String> mockResponse = ResponseEntity.ok("Sample directions JSON response");
        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(mockResponse);

        String origin = "123 Main St";
        String destination = "456 Elm St";

        String directions = googleMapsService.getDirections(origin, destination);

        assertNotNull(directions);
        assertEquals("Sample directions JSON response", directions);
    }

    @Test
    public void testGetDirections_Error() {
        // Mock an error response from the Google Maps API
        ResponseEntity<String> mockErrorResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error message");
        when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(mockErrorResponse);

        String origin = "123 Main St";
        String destination = "456 Elm St";

        String directions = googleMapsService.getDirections(origin, destination);

        assertNull(directions);
    }
}

