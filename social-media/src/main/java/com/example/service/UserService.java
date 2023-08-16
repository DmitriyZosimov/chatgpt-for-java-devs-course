package com.example.service;

import com.example.domain.User;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    User followUser(Long followerId, Long followedId);
    // Other methods related to User
}
