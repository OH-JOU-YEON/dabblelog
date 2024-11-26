package com.dabblelog.side.domain;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Entity
public class PostImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "PostContent_id")
    @ManyToOne
    private Long contentId;

    @Column(name = "img_route")
    private String route;
}
