package com.dabblelog.side.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@Entity
public class PostImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "PostContent_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PostContent contentId;

    @Column(name = "img_route")
    private String route;
}
