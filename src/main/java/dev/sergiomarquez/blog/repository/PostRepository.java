package dev.sergiomarquez.blog.repository;

import dev.sergiomarquez.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Iterable<Post> findAllByOrderByIdDesc();

}
