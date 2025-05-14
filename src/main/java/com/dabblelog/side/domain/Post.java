package com.dabblelog.side.domain;


import com.dabblelog.side.domain.dto.PostModifyDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

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

    //블로그 아이디마다 제목은 한 번만 쓸 수 있게 검사 추가

    private String title;

    @Column(name = "temp_post")
    private boolean temp = false;


    @Column(length = 50000)
    private String content;

    private String uuid;



    private Long likeCount;


    private LocalDateTime createdDay;


    //시리즈 없는 게시물 작성

    public Post(Blog blog, String title, boolean temp, String content) {
        this.blogId = blog;

        this.title = title;



        this.content = content;


        this.createdDay = LocalDateTime.now();

        this.uuid =  UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0,8);


        this.likeCount = 0L;

        this.temp = temp;
    }

    //시리즈 있는 게시물 작성

    public Post(Blog blog, String title,Series series, boolean temp, String content) {
        this.blogId = blog;

        this.content = content;

        this.seriesId = series;

        this.title = title;

        LocalDate now = LocalDate.now();

        this.uuid =  UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0,8);

        this.createdDay = LocalDateTime.now();

        this.likeCount = 0L;

        this.temp = temp;
    }

    public Post updatePost(PostModifyDTO postModifyDTO,Series series) {

        this.title = postModifyDTO.getTitle();

        this.content = postModifyDTO.getContent();

        this.seriesId = series;

        return this;
    }

    //임시 저장 포스트 저장으로 돌릴 때 사용. 시리즈 업데이트 하고 임시저장에서 저장으로 돌림

    public Post saveUpdate(Series series) {
        this.temp = false;

        this.seriesId = series;

        return this;
    }

    public Post modifyLikeCount(Long count) {
        this.likeCount = count;

        return this;
    }

}
