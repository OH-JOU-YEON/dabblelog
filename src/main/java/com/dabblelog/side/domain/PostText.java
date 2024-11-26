package com.dabblelog.side.domain;

import jakarta.persistence.*;

@Entity
public class PostText {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_content_id")
    private Long contentId;

    @Column(name = "text_detail")
    private String textDetail;
}
