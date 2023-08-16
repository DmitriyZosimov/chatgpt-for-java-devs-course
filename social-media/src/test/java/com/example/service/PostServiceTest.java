package com.example.service;

import com.example.domain.Post;
import com.example.domain.User;
import com.example.repository.PostRepository;
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
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostServiceImpl postService;

    @Test
    public void testCreatePost() {
        Post newPost = new Post();
        when(postRepository.save(any(Post.class))).thenReturn(newPost);

        Post savedPost = postService.createPost(newPost);

        assertNotNull(savedPost);
        verify(postRepository, times(1)).save(newPost);
    }

    @Test
    public void testGetPostById() {
        Long postId = 1L;
        Post post = new Post();
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        Post retrievedPost = postService.getPostById(postId);

        assertNotNull(retrievedPost);
        verify(postRepository, times(1)).findById(postId);
    }

    @Test
    public void testLikePost() {
        Long postId = 1L;
        Long userId = 2L;
        Post post = new Post();
        User user = new User();
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Post result = postService.likePost(postId, userId);

        assertNotNull(result);
        assertTrue(result.getLikedBy().contains(user));
        verify(postRepository, times(1)).save(post);
    }

    // Additional test methods for other PostService methods
}

