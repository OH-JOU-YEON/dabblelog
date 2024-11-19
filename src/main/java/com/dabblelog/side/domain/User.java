package com.dabblelog.side.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    private String readme;

    @Column
    private String picture;



    @Builder
    public User(String name, String email) {
        this.name = name;
        this.email = email;


    }

    public User update(String name) {
        this.name = name;


        return this;
    }


}