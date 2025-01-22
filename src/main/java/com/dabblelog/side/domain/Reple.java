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
    private User replyUser;

    private LocalDateTime createdDay;


    @Column(length = 50000)
    String content;

}
