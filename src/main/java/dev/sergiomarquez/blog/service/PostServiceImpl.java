package dev.sergiomarquez.blog.service;

import dev.sergiomarquez.blog.entity.Post;
import dev.sergiomarquez.blog.repository.PostRepository;
import dev.sergiomarquez.blog.utils.date.DateUtils;
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

}
