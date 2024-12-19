package com.dabblelog.side.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Calendar;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "blog_id")
    private Blog blogId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id")
    private Series seriesId;

    private String title;

    @Column(name = "temp_post")
    private boolean temp;



    private int likeCount;

    private int createdYear;

    private int createdMonth;

    private int createdDay;


    //시리즈 없는 게시물 작성

    public Post(Blog blog, String title, boolean temp) {
        this.blogId = blog;

        this.title = title;

        LocalDate now = LocalDate.now();

        this.createdYear = now.getYear();

        this.createdMonth = Integer.parseInt(now.getMonth().toString());

        this.createdDay = now.getDayOfMonth();

        this.likeCount = 0;

        this.temp = temp;
    }

    //시리즈 있는 게시물 작성

    public Post(Blog blog, String title,Series series, boolean temp) {
        this.blogId = blog;

        this.seriesId = series;

        this.title = title;

        LocalDate now = LocalDate.now();

        this.createdYear = now.getYear();

        this.createdMonth = Integer.parseInt(now.getMonth().toString());

        this.createdDay = now.getDayOfMonth();

        this.likeCount = 0;

        this.temp = temp;
    }

}
