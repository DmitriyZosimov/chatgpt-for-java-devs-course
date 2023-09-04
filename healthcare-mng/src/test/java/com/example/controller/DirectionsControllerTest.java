package com.example.controller;

import com.example.service.GoogleMapsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DirectionsController.class)
public class DirectionsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GoogleMapsService googleMapsService;

    @Test
    public void testGetDirections_Success() throws Exception {
        String origin = "123 Main St";
        String destination = "456 Elm St";
        String sampleDirections = "Sample directions JSON response";

        when(googleMapsService.getDirections(origin, destination)).thenReturn(sampleDirections);

        mockMvc.perform(get("/directions")
                        .param("origin", origin)
                        .param("destination", destination))
                .andExpect(status().isOk())
                .andExpect(content().string(sampleDirections));
    }

    @Test
    public void testGetDirections_Error() throws Exception {
        String origin = "123 Main St";
        String destination = "456 Elm St";

        when(googleMapsService.getDirections(origin, destination)).thenReturn(null);

        mockMvc.perform(get("/directions")
                        .param("origin", origin)
                        .param("destination", destination))
                .andExpect(status().isBadRequest());
    }
}

