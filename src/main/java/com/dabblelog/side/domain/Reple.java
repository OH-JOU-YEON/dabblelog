package com.dabblelog.side.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class Reple {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Post postId;

    @ManyToOne
    private Reple parentReple;

    @ManyToOne
    private Reple rootReple;

    @ManyToOne
    private User author;

    private LocalDateTime createdDay;


    @Column(length = 50000)
    String content;


    public Reple(Post postId,User author,LocalDateTime localDateTime,String content) {

        this.author = author;

        this.postId = postId;

        this.createdDay = localDateTime;

        this.rootReple = this;

        this.content = content;
    }

    public Reple(Post postId,User author,Reple parentReple,LocalDateTime localDateTime,String content) {

        this.postId = postId;

        this.author = author;

       this.parentReple = parentReple;

        Reple root = this.getParentReple();

        while(root != null) {
            root = root.getParentReple();

        }

        this.rootReple = root;

        this.createdDay = localDateTime;

        this.content = content;


    }

}
