package com.dabblelog.side.domain;

import jakarta.persistence.*;

@Entity
public class PostImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long id;

    @JoinColumn(name = "PostContent_id")
    private Long contentId;

    @Column(name = "img_route")
    private String route;
}
