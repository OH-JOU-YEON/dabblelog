package com.dabblelog.side.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;

@Entity
@Getter
public class Blog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String blogName;

    @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "user_id")
    private User userId;


    public Blog(User user) {
        this.userId = user;

        String[] parseEmail = user.getEmail().split("@");

        this.blogName = parseEmail[0] + ".log";

    }


}
