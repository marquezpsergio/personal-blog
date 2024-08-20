package dev.sergiomarquez.blog.comment.repository;

import dev.sergiomarquez.blog.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Iterable<Comment> findAllByPostIdOrderByIdDesc(Long postId);

}
