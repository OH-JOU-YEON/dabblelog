package com.dabblelog.side.domain;

import jakarta.persistence.*;

public class TagMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_tag_id")
    private Long tagId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Long postId;
}
