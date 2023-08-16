package com.example.controller;

import com.example.domain.User;
import com.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Optional;
import static org.mockito.Mockito.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testCreateUser() throws Exception {
        User newUser = new User();
        newUser.setId(1L);
        newUser.setUsername("testuser");
        when(userService.createUser(any(User.class))).thenReturn(newUser);

        mockMvc.perform(post("/api/users")
                        .contentType("application/json")
                        .content("{\"username\": \"testuser\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("testuser"));

        verify(userService, times(1)).createUser(any(User.class));
    }

    @Test
    public void testGetUser() throws Exception {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        when(userService.getUserById(userId)).thenReturn(user);

        mockMvc.perform(get("/api/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId));

        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    public void testFollowUser() throws Exception {
        Long followerId = 1L;
        Long followedId = 2L;
        User follower = new User();
        follower.setId(followerId);
        when(userService.followUser(eq(followerId), eq(followedId))).thenReturn(follower);

        mockMvc.perform(post("/api/users/{followerId}/follow/{followedId}", followerId, followedId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(followerId));

        verify(userService, times(1)).followUser(eq(followerId), eq(followedId));
    }

    // Additional test methods for other UserController methods
}

