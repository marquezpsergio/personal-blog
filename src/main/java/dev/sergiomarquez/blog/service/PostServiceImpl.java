package dev.sergiomarquez.blog.service;

import dev.sergiomarquez.blog.entity.Post;
import dev.sergiomarquez.blog.repository.PostRepository;
import dev.sergiomarquez.blog.utils.date.DateUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post save(Post post) {
        post.setLikeCount(0);
        post.setViewCount(0);
        post.setDate(DateUtils.getInstant());

        return postRepository.save(post);
    }

    @Override
    public Iterable<Post> saveAll(Iterable<Post> posts) {
        posts.forEach(post -> {
            post.setLikeCount(0);
            post.setViewCount(0);
            post.setDate(DateUtils.getInstant());
        });
        return postRepository.saveAll(posts);
    }

    public Iterable<Post> getAllPosts() {
        return postRepository.findAllByOrderByIdDesc();
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .map(post -> {
                    post.setViewCount(post.getViewCount() + 1);
                    return postRepository.save(post);
                })
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
    }

}
