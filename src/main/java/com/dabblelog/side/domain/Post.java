package com.dabblelog.side.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "blog_id")
    private Long blogId;

    @Column(name = "series_id")
    private Long seriesId;

    private String title;

    private int contentId;

    @Column(name = "temp_post")
    private boolean temp;

}
