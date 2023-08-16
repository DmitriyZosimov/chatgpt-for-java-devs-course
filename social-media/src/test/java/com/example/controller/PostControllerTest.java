package com.example.controller;

import com.example.domain.Post;
import com.example.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    public void testCreatePost() throws Exception {
        Post newPost = new Post();
        newPost.setTitle("Test Post");
        when(postService.createPost(any(Post.class))).thenReturn(newPost);

        mockMvc.perform(post("/api/posts")
                        .contentType("application/json")
                        .content("{\"title\": \"Test Post\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Test Post"));

        verify(postService, times(1)).createPost(any(Post.class));
    }

    @Test
    public void testGetPost() throws Exception {
        Long postId = 1L;
        Post post = new Post();
        post.setId(postId);
        when(postService.getPostById(postId)).thenReturn(post);

        mockMvc.perform(get("/api/posts/{id}", postId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(postId));

        verify(postService, times(1)).getPostById(postId);
    }

    @Test
    public void testLikePost() throws Exception {
        Long postId = 1L;
        Long userId = 2L;
        Post post = new Post();
        post.setId(postId);
        when(postService.likePost(eq(postId), eq(userId))).thenReturn(post);

        mockMvc.perform(post("/api/posts/{postId}/like/{userId}", postId, userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(postId));

        verify(postService, times(1)).likePost(eq(postId), eq(userId));
    }

    // Additional test methods for other PostController methods
}

