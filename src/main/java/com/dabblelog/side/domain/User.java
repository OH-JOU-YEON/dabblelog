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
    @Column(name = "user_name")
    private String name;

    @NotNull
    @Column(name = "email")
    private String email;

    @Column(name="readme")
    private String readme;

    @Column(name="picture")
    private String picture;

    @Enumerated(EnumType.STRING) // Enum 타입은 문자열 형태로 저장해야 함
    @NotNull
    @Column(name="role")
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

    public String getRoleKey() {
        return this.role.getKey();
    }


}