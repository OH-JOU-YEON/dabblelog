package com.dabblelog.side.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "blog_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Blog blogId;

    private String color;

    @Column(unique = true)
    private String title;


    public Series(Blog blog,String color,String title) {
        this.blogId = blog;

        this.title = title;
        this.color = color;
    }

    public Series update(String color, String title) {
        this.color = color;
        this.title = title;


        return this;
    }

}
