package dev.sergiomarquez.blog.controller;

import dev.sergiomarquez.blog.entity.Post;
import dev.sergiomarquez.blog.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin("*")
public class PostController {

    private final PostService postService;

    Logger logger = Logger.getLogger(PostController.class.getName());

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        try {
            Post createdPost = postService.save(post);
            logger.info("Post created: " + createdPost);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
        } catch (Exception e) {
            logger.severe("Error creating post: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/multi")
    public ResponseEntity<Iterable<Post>> createPosts(@RequestBody Iterable<Post> posts) {
        try {
            Iterable<Post> createdPosts = postService.saveAll(posts);
            logger.info("Posts created: " + createdPosts);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPosts);
        } catch (Exception e) {
            logger.severe("Error creating posts: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<Post>> getAllPosts() {
        try {
            Iterable<Post> posts = postService.getAllPosts();
            return ResponseEntity.ok(posts);
        } catch (Exception e) {
            logger.severe("Error getting posts: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Long postId) {
        try {
            Post post = postService.getPostById(postId);
            return ResponseEntity.ok(post);
        } catch (EntityNotFoundException e) {
            logger.severe("Post not found: " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{postId}/like")
    public ResponseEntity<Post> likePost(@PathVariable Long postId) {
        try {
            postService.likePost(postId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            logger.severe("Post not found: " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<Iterable<Post>> searchByTitle(@PathVariable String title) {
        try {
            Iterable<Post> posts = postService.searchByTitle(title);
            return ResponseEntity.ok(posts);
        } catch (Exception e) {
            logger.severe("Error searching posts: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
}
