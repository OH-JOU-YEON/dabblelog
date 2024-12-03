package com.dabblelog.side.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String blogName;

    @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "user_id")
    private User userId;


    Blog(User user, String userEmail) {
        this.userId = user;

        String[] parseEmail = userEmail.split("@");

        this.blogName = parseEmail[0] + ".log";

    }
}
