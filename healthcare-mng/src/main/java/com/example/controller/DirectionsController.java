package com.example.controller;

import com.example.service.GoogleMapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/directions")
public class DirectionsController {
    private final GoogleMapsService googleMapsService;

    @Autowired
    public DirectionsController(GoogleMapsService googleMapsService) {
        this.googleMapsService = googleMapsService;
    }

    @GetMapping
    public ResponseEntity<String> getDirections(
            @RequestParam String origin,
            @RequestParam String destination
    ) {
        String directions = googleMapsService.getDirections(origin, destination);

        if (directions != null) {
            return ResponseEntity.ok(directions);
        } else {
            return ResponseEntity.badRequest().body("Error retrieving directions.");
        }
    }
}

