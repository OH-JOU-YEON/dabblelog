package com.dabblelog.side.domain;


import jakarta.persistence.*;

@Entity
public class ReReple {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    private Reple parentReple;

    private Post postId;


}
