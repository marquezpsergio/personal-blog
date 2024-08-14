package dev.sergiomarquez.blog.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
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

    private String title;

    @Column(length = 5000)
    private String content;

    private String author;

    private String image;

    @Temporal(TemporalType.DATE)
    private Date date;

    private int likeCount;

    private int viewCount;

    @ElementCollection
    private List<String> tags;

}
