package dev.sergiomarquez.blog.post.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @Column(length = 5000)
    @NotBlank
    private String content;

    private String author;

    private String image;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant date;

    private int likeCount;

    private int viewCount;

    @ElementCollection
    private List<String> tags;

    public Post(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
