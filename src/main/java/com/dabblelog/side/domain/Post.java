package com.dabblelog.side.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
   @JoinColumn(name = "blog_id")
    private Long blogId;

    @ManyToOne
    @JoinColumn(name = "series_id")
    private Long seriesId;

    private String title;

    @OneToOne
    @JoinColumn(name = "post_content_id")
    private Long contentId;

    @Column(name = "temp_post")
    private boolean temp;



    private int likeCount;

    private int createdYear;

    private int createdMonth;

    private int createdDay;


}
