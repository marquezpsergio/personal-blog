package dev.sergiomarquez.blog.service;

import dev.sergiomarquez.blog.entity.Comment;

public interface CommentService {

    Comment createComment(Long postId, String author, String content);

    Iterable<Comment> getCommentsByPostId(Long postId);

}
