package dev.sergiomarquez.blog.post.service;

import dev.sergiomarquez.blog.post.entity.Post;

public interface PostService {

    Post save(Post post);

    Iterable<Post> saveAll(Iterable<Post> posts);

    Iterable<Post> getAllPosts();

    Post getPostById(Long id);

    void likePost(long postId);

    Iterable<Post> searchByTitle(String title);
}
