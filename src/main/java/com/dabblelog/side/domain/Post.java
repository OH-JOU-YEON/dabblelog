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

    @ManyToOne
   @JoinColumn(name = "blog_id")
    private Long blogId;

    @OneToMany
    @JoinColumn(name = "series_id")
    private Long seriesId;

    private String title;

    @OneToOne
    @JoinColumn(name = "post_content_id")
    private Long contentId;

    @Column(name = "temp_post")
    private boolean temp;

    @OneToMany
    @JoinColumn(name = "post_tag_id")
    private Long tagId;


}
