package dev.sergiomarquez.blog.comment.service;

import dev.sergiomarquez.blog.comment.entity.Comment;
import dev.sergiomarquez.blog.comment.repository.CommentRepository;
import dev.sergiomarquez.blog.post.entity.Post;
import dev.sergiomarquez.blog.post.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public Comment createComment(Long postId, String author, String content) {
        Optional<Post> post = postRepository.findById(postId);

        if (post.isPresent()) {
            Comment comment = new Comment();
            comment.setPost(post.get());
            comment.setContent(content);
            comment.setAuthor(author);
            comment.setDate(Instant.now());

            return commentRepository.save(comment);
        }

        throw new EntityNotFoundException("Post not found");

    }

    public Iterable<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findAllByPostIdOrderByIdDesc(postId);
    }

}
