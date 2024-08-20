package dev.sergiomarquez.blog.controller;

import dev.sergiomarquez.blog.entity.Comment;
import dev.sergiomarquez.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin("*")
public class CommentController {

    private final CommentService commentService;

    private final Logger logger = Logger.getLogger(CommentController.class.getName());

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestParam Long postId, @RequestParam String author, @RequestParam String content) {
        try {
            Comment createdComment = commentService.createComment(postId, author, content);
            logger.info("Comment created: " + createdComment);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
        } catch (Exception e) {
            logger.severe("Error creating comment: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<Iterable<Comment>> getCommentsByPostId(@PathVariable Long postId) {
        try {
            Iterable<Comment> comments = commentService.getCommentsByPostId(postId);
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            logger.severe("Error getting comments: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
