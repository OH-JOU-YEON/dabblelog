package com.dabblelog.side.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;

@Entity
@Getter
public class Blog implements Serializable {

    @Id
    @Column(name = "member_id", nullable = false)
    private Long user_id;

    @Column(name = "name")
    private String blogName;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
   @JoinColumn(name = "user_id", unique = true)
    private User user;


    public Blog(User user) {
        this.user = user;

        String[] parseEmail = user.getEmail().split("@");

        this.blogName = parseEmail[0] + ".log";

    }



}
