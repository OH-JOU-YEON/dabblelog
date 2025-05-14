package com.dabblelog.side.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Entity
public class Reple {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Post postId;

    private String uuid;


    @ManyToOne
    private User author;

    private LocalDateTime createdDay;


    @Column(length = 50000)
    String content;


    //댓글 생성자

    public Reple(Post postId,User author,LocalDateTime localDateTime,String content) {

        this.author = author;

        this.postId = postId;

        this.createdDay = localDateTime;

        this.uuid =  UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0,8);

        this.content = content;
    }

    //답글 생성자

    public Reple updateReple(String content) {

        this.content = content;

        return this;
    }


}
