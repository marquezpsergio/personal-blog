package dev.sergiomarquez.blog.post.repository;

import dev.sergiomarquez.blog.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Iterable<Post> findAllByOrderByIdDesc();

    Iterable<Post> findAllByTitleContainingIgnoringCaseOrderByIdDesc(String title);

}
