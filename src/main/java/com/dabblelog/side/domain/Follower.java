package com.dabblelog.side.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id_fk", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User following_id;

    @JoinColumn(name = "user_id" , insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User followed_id;


    public Follower(User followingUser,User followedUser) {
        this.following_id = followingUser;
        this.followed_id = followedUser;
    }

}
