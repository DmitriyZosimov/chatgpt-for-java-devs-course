package com.example.service;

import com.example.domain.User;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testCreateUser() {
        User newUser = new User();
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        User savedUser = userService.createUser(newUser);

        assertNotNull(savedUser);
        verify(userRepository, times(1)).save(newUser);
    }

    @Test
    public void testGetUserById() {
        Long userId = 1L;
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User retrievedUser = userService.getUserById(userId);

        assertNotNull(retrievedUser);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void testFollowUser() {
        Long followerId = 1L;
        Long followedId = 2L;
        User follower = new User();
        User followed = new User();
        when(userRepository.findById(followerId)).thenReturn(Optional.of(follower));
        when(userRepository.findById(followedId)).thenReturn(Optional.of(followed));

        User result = userService.followUser(followerId, followedId);

        assertNotNull(result);
        assertTrue(result.getFollows().contains(followed));
        verify(userRepository, times(1)).save(follower);
    }

    // Additional test methods for other UserService methods
}

