package com.dabblelog.side.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User userId;


    @ManyToOne(fetch = FetchType.LAZY)
    private Post postId;


    public Favorite(User user,Post post) {

        this.userId = user;

        this.postId = post;
    }

}
