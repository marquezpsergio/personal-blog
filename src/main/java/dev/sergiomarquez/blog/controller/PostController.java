package dev.sergiomarquez.blog.controller;

import dev.sergiomarquez.blog.entity.Post;
import dev.sergiomarquez.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/posts")
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
            return createResponse(createdPost);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    private ResponseEntity<Post> createResponse(Post post) {
        logger.info("Post created: " + post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    private ResponseEntity<Post> handleException(Exception e) {
        logger.severe("Error creating post: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
