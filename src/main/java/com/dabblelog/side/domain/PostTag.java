package com.dabblelog.side.domain;

import jakarta.persistence.*;

@Entity
public class PostTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;


}
