package com.example.controller;

import com.example.domain.Post;
import com.example.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post newPost = postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{postId}/like/{userId}")
    public ResponseEntity<Post> likePost(@PathVariable Long postId, @PathVariable Long userId) {
        Post likedPost = postService.likePost(postId, userId);
        if (likedPost != null) {
            return ResponseEntity.ok(likedPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Other endpoints related to Post
}
