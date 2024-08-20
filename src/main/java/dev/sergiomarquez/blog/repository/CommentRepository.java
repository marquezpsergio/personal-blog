package dev.sergiomarquez.blog.repository;

import dev.sergiomarquez.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Iterable<Comment> findAllByPostIdOrderByIdDesc(Long postId);

}
