package com.dabblelog.side.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private boolean temp = false;

    private String content;



    private int likeCount;

    private int createdYear;

    private int createdMonth;

    private int createdDay;


    //시리즈 없는 게시물 작성

    public Post(Blog blog, String title, boolean temp, String content) {
        this.blogId = blog;

        this.title = title;

        this.content = content;

        LocalDate now = LocalDate.now();

        this.createdYear = now.getYear();

        this.createdMonth = Integer.parseInt(now.getMonth().toString());

        this.createdDay = now.getDayOfMonth();

        this.likeCount = 0;

        this.temp = temp;
    }

    //시리즈 있는 게시물 작성

    public Post(Blog blog, String title,Series series, boolean temp, String content) {
        this.blogId = blog;

        this.content = content;

        this.seriesId = series;

        this.title = title;

        LocalDate now = LocalDate.now();

        this.createdYear = now.getYear();

        this.createdMonth = Integer.parseInt(now.getMonth().toString());

        this.createdDay = now.getDayOfMonth();

        this.likeCount = 0;

        this.temp = temp;
    }

    //임시 저장 포스트 ㄹㅇ 저장으로 돌릴 때 사용. 시리즈 업데이트 하고 임시저장에서 저장으로 돌림

    public Post saveUpdate(Series series) {
        this.temp = false;

        this.seriesId = series;

        return this;
    }

}
