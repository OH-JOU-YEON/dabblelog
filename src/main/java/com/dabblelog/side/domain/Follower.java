package com.dabblelog.side.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id_fk")
    @ManyToOne(fetch = FetchType.LAZY)
    private User followingId;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User followedId;


    public Follower(User followingUser,User followedUser) {
        this.followingId = followingUser;
        this.followedId = followedUser;
    }

}
