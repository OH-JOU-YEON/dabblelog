package com.dabblelog.side.domain;

import jakarta.persistence.*;

@Entity
public class PostContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "post_id")
    private Long postId;
}
