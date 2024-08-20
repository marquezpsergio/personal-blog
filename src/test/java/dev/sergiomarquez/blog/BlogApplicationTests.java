package dev.sergiomarquez.blog;

import dev.sergiomarquez.blog.post.entity.Post;
import dev.sergiomarquez.blog.post.repository.PostRepository;
import dev.sergiomarquez.blog.post.service.PostService;
import dev.sergiomarquez.blog.post.service.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BlogApplicationTests {

    @Mock
    private PostRepository postRepository;

    private PostService postService;

    @BeforeEach
    void setUp() {
        postService = new PostServiceImpl(postRepository);
    }

    private Post crearPostDePrueba() {
        return new Post("Título de prueba", "Contenido de prueba", "Autor de prueba");
    }

    private Post crearPostConTags() {
        Post post = crearPostDePrueba();
        post.setTags(Arrays.asList("tag1", "tag2"));
        return post;
    }

    @Test
    void testSavePost() {
        Post post = crearPostDePrueba();

        Mockito.when(postRepository.save(post)).thenReturn(post);
        Post savedPost = postService.save(post);

        assertEquals(post, savedPost);
        verify(postRepository, times(1)).save(argThat(p -> p.equals(post)));
    }

    @Test
    void testSavePostWithInvalidFields() {
        Post postWithEmptyFields = new Post("", "", "");
        Post postWithNullFields = new Post(null, null, null);

        Mockito.when(postRepository.save(postWithEmptyFields)).thenThrow(new IllegalArgumentException("Los campos del post no pueden estar vacíos"));
        Mockito.when(postRepository.save(postWithNullFields)).thenThrow(new IllegalArgumentException("Los campos del post no pueden estar vacíos"));

        assertThrows(IllegalArgumentException.class, () -> postService.save(postWithEmptyFields));
        assertThrows(IllegalArgumentException.class, () -> postService.save(postWithNullFields));
    }

    @Test
    void testSavePostWithCreationDate() {
        Post post = crearPostDePrueba();

        Mockito.when(postRepository.save(post)).thenReturn(post);
        Post savedPost = postService.save(post);

        assertNotNull(savedPost.getDate());
        assertTrue(Instant.now().minusSeconds(1).isBefore(savedPost.getDate()));
        assertTrue(Instant.now().plusSeconds(1).isAfter(savedPost.getDate()));
    }

    @Test
    void testSavePostWithTags() {
        Post post = crearPostConTags();

        Mockito.when(postRepository.save(post)).thenReturn(post);
        Post savedPost = postService.save(post);

        assertEquals(post.getTags(), savedPost.getTags());
    }
}
