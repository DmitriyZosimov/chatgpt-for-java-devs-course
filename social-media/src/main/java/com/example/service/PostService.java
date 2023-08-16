package com.example.service;

import com.example.domain.Post;

public interface PostService {
    Post createPost(Post post);
    Post getPostById(Long id);
    Post likePost(Long postId, Long userId);
    // Other methods related to Post
}
