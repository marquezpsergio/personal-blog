package dev.sergiomarquez.blog.comment.service;

import dev.sergiomarquez.blog.comment.entity.Comment;

public interface CommentService {

    Comment createComment(Long postId, String author, String content);

    Iterable<Comment> getCommentsByPostId(Long postId);

}
