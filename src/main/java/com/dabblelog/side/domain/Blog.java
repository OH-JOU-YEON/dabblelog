package com.dabblelog.side.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Entity
@Getter
public class Blog {

    @Id
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String blogName;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
   @JoinColumn(name = "user_id", unique = true)
    private User user;

    @Builder
    public Blog(User user) {

        this.user = user;

        String[] parseEmail = user.getEmail().split("@");

        this.blogName = parseEmail[0] + ".log";

    }

    public Blog update(User user) {

        String[] parseEmail = user.getEmail().split("@");

        this.blogName = parseEmail[0] + ".log";

        return this;
    }


}
