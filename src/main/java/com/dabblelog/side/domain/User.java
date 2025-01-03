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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;


    @NotNull
    @Column(unique = true)
    private String email;


    private String readme;


    private String picture = "/img/person-circle.svg";

    @Enumerated(EnumType.STRING) // Enum 타입은 문자열 형태로 저장해야 함
    private Role role;



    @Builder
    public User(String name, String email, Role role) {
        this.name = name;
        this.email = email;
        this.role = role;


    }

    public User update(String name) {
        this.name = name;


        return this;
    }

    public User updateReadme(String readme) {
        this.readme = readme;

        return this;
    }

    public User updatePicture(String picture) {
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }


}